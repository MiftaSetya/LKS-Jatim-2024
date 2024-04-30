package com.example.eduspark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eduspark.databinding.ActivityMenuBinding
import com.example.eduspark.databinding.ListGameBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.net.URL

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        GlobalScope.launch(Dispatchers.IO) {
            val con = URL("${Session.url}/games").openStream().bufferedReader().readText()
            var games = JSONArray(con)

            class GamesViewHolder(val binding: ListGameBinding) : RecyclerView.ViewHolder(binding.root)
            runOnUiThread {
                var adapter = object : RecyclerView.Adapter<GamesViewHolder>() {
                    override fun onCreateViewHolder(
                        parent: ViewGroup,
                        viewType: Int
                    ): GamesViewHolder {
                        val binding = ListGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                        return GamesViewHolder(binding)
                    }

                    override fun getItemCount(): Int {
                        return games.length()
                    }

                    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
                        val item = games.getJSONObject(position)

                        holder.binding.gameNameTV.text = item.getString("name")
                        holder.binding.categoryTV.text = item.getString("category")
                        holder.binding.playerTV.text = "${item.getInt("totalPlayer")} players"

                        holder.itemView.setOnClickListener {
                            val intent = Intent(this@MenuActivity, GameActivity::class.java).apply {
                                putExtra("id", item.getInt("id"))
                            }
                            startActivity(intent)
                        }
                    }
                }
                binding.MenuRV.adapter = adapter
                binding.MenuRV.layoutManager = LinearLayoutManager(this@MenuActivity)
            }
        }
    }
}