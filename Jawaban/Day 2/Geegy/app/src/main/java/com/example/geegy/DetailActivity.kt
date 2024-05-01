package com.example.geegy

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.geegy.databinding.ActivityDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.backBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        var gameId = intent.getIntExtra("id", 0)

        GlobalScope.launch(Dispatchers.IO) {
            val con = URL("${Session.url}/appointments/$gameId").openStream().bufferedReader().readText()
            val details = JSONObject(con)

            runOnUiThread {
                binding.appointmentDateTV.text = details.getString("appointment_date")
                binding.nameTV.text = "Name: ${details.getString("patient_name")}"

                var dob = details.getString("patient_dob")
                var age = details.getInt("patient_age")
                binding.dobTV.text = "DOB: $dob ($age years old)"

                binding.addressTV.text = details.getString("patient_address")
                binding.nameDoctorTV.text = "Name: ${details.getString("doctor_name")}"
                binding.specialisTV.text = "Specialist: ${details.getString("doctor_specialist")}"
                binding.problemTV.text = details.getString("problems")
                binding.symptomsTV.text = details.getString("symptoms")
                binding.actionTV.text = details.getString("actions")

                var imagePath = details.getString("teeth_photo")

                GlobalScope.launch(Dispatchers.IO) {
                    val con = URL("http://10.0.2.2:5000/images/$imagePath").openConnection() as HttpURLConnection

                    val inputStream = con.inputStream
                    val imageBitmap = BitmapFactory.decodeStream(inputStream)

                    runOnUiThread {
                        binding.teethImage.setImageBitmap(imageBitmap)
                    }
                }

                binding.teethImage.setOnClickListener {
                    runOnUiThread {
                        val dialogView = LayoutInflater.from(this@DetailActivity).inflate(R.layout.detail_image, null)

                        val dialogViewImage = dialogView.findViewById<ImageView>(R.id.detailImage)

                        GlobalScope.launch(Dispatchers.IO) {
                            val con = URL("http://10.0.2.2:5000/images/$imagePath").openConnection() as HttpURLConnection

                            val inputStream = con.inputStream
                            val imageBitmap = BitmapFactory.decodeStream(inputStream)

                            runOnUiThread {
                                dialogViewImage.setImageBitmap(imageBitmap)
                            }
                        }

                        val alertDialogBuilder = AlertDialog.Builder(this@DetailActivity)
                        alertDialogBuilder.setView(dialogView)
                        alertDialogBuilder.setCancelable(true)

                        val alertDialog = alertDialogBuilder.create()
                        alertDialog.show()
                    }
                }
            }
        }
    }
}