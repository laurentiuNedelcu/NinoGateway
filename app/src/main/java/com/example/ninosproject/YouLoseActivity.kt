package com.example.ninosproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class YouLoseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_you_lose)

        val retry_level: Button = findViewById(R.id.retryButtonYL)
        retry_level.setOnClickListener {
            //val intent: Intent = Intent(this, clsse partida)//falta per implementar
            //startActivity(intent)
        }

        val levels: Button = findViewById(R.id.levelsButtonYL)
        levels.setOnClickListener {
            val intent: Intent = Intent(this, LevelActivity::class.java)
            startActivity(intent)
        }

    }
}
