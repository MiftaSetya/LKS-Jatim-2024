package com.example.eduspark

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.eduspark.databinding.ActivityGameBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var words : JSONArray
    private var currentIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val gameId = intent.getIntExtra("id", 0)

        GlobalScope.launch(Dispatchers.IO) {
            val con = URL("${Session.url}/words/$gameId").openStream().bufferedReader().readText()
            words = JSONArray(con)

            runOnUiThread {
                updateWordsAndImage()
                binding.leftBtn.setOnClickListener { prevWord() }
                binding.rightBtn.setOnClickListener { nextWord() }
            }
        }
    }

    private fun updateWordsAndImage() {
        if (currentIndex == 0) {
            binding.leftBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.SparkLightGray))
            binding.leftBtn.setOnClickListener {
                startActivity(Intent(this, MenuActivity::class.java))
            }
        }
        else if (currentIndex == words.length() - 1){
            binding.rightBtn.text = "FINISH"
            binding.leftBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.SparkBlue))
        } else {
            binding.rightBtn.text = "NEXT"
        }

        val item = words.getJSONObject(currentIndex)
        var text = item.getString("word")
        var textArray = text.toCharArray()
        textArray.shuffle()
        var shuffleText = String(textArray)
        binding.wordsTV.text = shuffleText

        var imagePath = item.getString("image")
        GlobalScope.launch(Dispatchers.IO) {
            val con = URL("http://10.0.2.2:5000/images/$imagePath").openConnection() as HttpURLConnection

            val inputStream = con.inputStream
            val imageBitmap = BitmapFactory.decodeStream(inputStream)

            runOnUiThread {
                binding.image.setImageBitmap(imageBitmap)
            }
        }
    }

    private fun nextWord() {
        val item = words.getJSONObject(currentIndex)
        var word = item.getString("word")
        var text = binding.textET.text.toString().uppercase()

        if (currentIndex < words.length() - 1) {
            currentIndex++
            binding.leftBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.SparkBlue))
            binding.leftBtn.setTextColor(ContextCompat.getColor(this, R.color.SparkWhite))
            updateWordsAndImage()

            if (word == text) {
                score += 10
            }
        }
        else {
            if (word == text) {
                score += 10
            }

            val intent = Intent(this, LeaderboardActivity::class.java).apply {
                putExtra("id", intent.getIntExtra("id", 0))
                putExtra("score", score)
            }

            startActivity(intent)
        }

        binding.textET.setText("")
    }

    private fun prevWord() {
        if (currentIndex > 0) {
            currentIndex--
            binding.leftBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.SparkBlue))
            binding.leftBtn.setTextColor(ContextCompat.getColor(this, R.color.SparkWhite))
            binding.textET.setText("")
            updateWordsAndImage()
        }
        else {
            binding.leftBtn.setOnClickListener {
                startActivity(Intent(this, MenuActivity::class.java))
            }
        }
    }
}