package com.example.ninosproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.Button

class YouWinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_you_win)

        val retry_level: Button = findViewById(R.id.retryButtonYW)
        retry_level.setOnClickListener {
            //val intent: Intent = Intent(this, clsse partida)//falta per implementar
            //startActivity(intent)
        }

        val levels: Button = findViewById(R.id.levelsbuttonYW)
        levels.setOnClickListener {
            val intent: Intent = Intent(this, LevelActivity::class.java)
            startActivity(intent)
        }

        val next_level: Button = findViewById(R.id.nextButtonYW)
        next_level.setOnClickListener {
            //val intent: Intent = Intent(this, seguent nivell)//falta per implementar
            //startActivity(intent)
        }

    }
}
