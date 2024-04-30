package com.example.eduspark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eduspark.databinding.ActivityLeaderboardBinding
import com.example.eduspark.databinding.ListLeaderboardBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class LeaderboardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLeaderboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var gameId = intent.getIntExtra("id", 0)

        binding.backBtn.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }

        var score = intent.getIntExtra("score", 0)
        binding.scoreTv.text = score.toString()

        getLeaderboard()

        binding.submitBtn.setOnClickListener {
            if (binding.nicknameET.text.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter nickname", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            GlobalScope.launch(Dispatchers.IO) {
                val con = URL("${Session.url}/leaderboards").openConnection() as HttpURLConnection
                con.requestMethod = "POST"
                con.setRequestProperty("Content-Type", "application/json")

                val data = JSONObject().apply {
                    put("gameID", gameId)
                    put("nickname", binding.nicknameET.text.toString())
                    put("totalPoint", score)
                }

                con.outputStream.write(data.toString().toByteArray())

                var code = con.responseCode

                if (code == 200) {
                    val msg = con.inputStream.bufferedReader().readText()

                    runOnUiThread {
                        Toast.makeText(this@LeaderboardActivity, msg, Toast.LENGTH_SHORT).show()
                        getLeaderboard()
                        binding.nicknameET.setText("")
                        binding.scoreTv.text = 0.toString()
                    }
                }
            }
        }
    }

    private fun getLeaderboard() {
        var gameId = intent.getIntExtra("id", 0)

        class LeaderboardViewHolder(val binding : ListLeaderboardBinding) : RecyclerView.ViewHolder(binding.root)
        GlobalScope.launch(Dispatchers.IO) {
            val con = URL("${Session.url}/leaderboards/$gameId").openStream().bufferedReader().readText()
            val leaderboards = JSONArray(con)

            runOnUiThread {
                var adapter = object : RecyclerView.Adapter<LeaderboardViewHolder>(){
                    override fun onCreateViewHolder(
                        parent: ViewGroup,
                        viewType: Int
                    ): LeaderboardViewHolder {
                        val binding = ListLeaderboardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                        return LeaderboardViewHolder(binding)
                    }

                    override fun getItemCount(): Int {
                        return leaderboards.length()
                    }

                    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
                        val item = leaderboards.getJSONObject(position)

                        var rank = (position + 1).toString()
                        holder.binding.noTV.text = "${rank}."

                        holder.binding.nameTV.text = item.getString("nickname")
                        holder.binding.scoreTV.text = item.getInt("totalPoint").toString()
                    }
                }
                binding.leaderboardRV.adapter = adapter
                binding.leaderboardRV.layoutManager = LinearLayoutManager(this@LeaderboardActivity)
            }
        }
    }
}