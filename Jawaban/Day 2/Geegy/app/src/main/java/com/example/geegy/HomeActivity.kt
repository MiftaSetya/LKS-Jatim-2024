package com.example.geegy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geegy.databinding.ActivityHomeBinding
import com.example.geegy.databinding.ListAppointmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.net.URL

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        class AppointmentViewHolder(val binding: ListAppointmentBinding) : RecyclerView.ViewHolder(binding.root)
        class AppointmentAdapter(val appoinments : JSONArray) : RecyclerView.Adapter<AppointmentViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
                val binding = ListAppointmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return AppointmentViewHolder(binding)
            }

            override fun getItemCount(): Int {
                return appoinments.length()
            }

            override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
                val item = appoinments.getJSONObject(position)

                holder.binding.nameTV.text = item.getString("patient_name")

                var dob = item.getString("patient_dob")
                var age = item.getInt("patient_age")
                holder.binding.dobTV.text = "DOB: ${dob} ($age years old)"

                holder.binding.dateTV.text = item.getString("appointment_date")
                holder.binding.problemTV.text = item.getString("problems")

                var cancelled = item.getBoolean("iscancelled")
                if (cancelled) {
                    holder.binding.cancelledTV.visibility = View.VISIBLE
                }
                else {
                    holder.binding.cancelledTV.visibility = View.GONE
                }

                holder.itemView.setOnClickListener {
                    val intent = Intent(this@HomeActivity, DetailActivity::class.java).apply {
                        putExtra("id", item.getInt("id"))
                    }
                    startActivity(intent)
                }
            }
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var date = binding.dateET.text.toString()

        GlobalScope.launch(Dispatchers.IO) {
            val con = URL("${Session.url}/appointments?date=$date").openStream().bufferedReader().readText()
            val appointments = JSONArray(con)
            Log.d("app", appointments.toString())

            runOnUiThread {
                binding.AppointmentRV.adapter = AppointmentAdapter(appointments)
                binding.AppointmentRV.layoutManager = LinearLayoutManager(this@HomeActivity)
            }
        }

        binding.dateET.addTextChangedListener { text ->
            GlobalScope.launch(Dispatchers.IO) {
                val con = URL("${Session.url}/appointments?date=$text").openStream().bufferedReader().readText()
                val appointments = JSONArray(con)

                runOnUiThread {
                    binding.AppointmentRV.adapter = AppointmentAdapter(appointments)
                    binding.AppointmentRV.layoutManager = LinearLayoutManager(this@HomeActivity)
                }
            }
        }

        binding.searchET.addTextChangedListener { text ->
            GlobalScope.launch(Dispatchers.IO) {
                val con = URL("${Session.url}/appointments?date?=$date&search=$text").openStream().bufferedReader().readText()
                val appointments = JSONArray(con)

                runOnUiThread {
                    binding.AppointmentRV.adapter = AppointmentAdapter(appointments)
                    binding.AppointmentRV.layoutManager = LinearLayoutManager(this@HomeActivity)
                }
            }
        }
    }
}