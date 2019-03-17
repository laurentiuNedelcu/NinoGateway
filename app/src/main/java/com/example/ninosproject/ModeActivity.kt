package com.example.ninosproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mode_seleccion)
        val back_button : Button = findViewById<Button>(R.id.button_back)
        val single_button : Button = findViewById<Button>(R.id.button_singleplayer)
        val multi_button : Button = findViewById<Button>(R.id.button_multiplayer)

        single_button.setOnClickListener {
            val intent: Intent = Intent(this, LevelActivity::class.java)
            intent.putExtra("mode","1 - Player")
            startActivity(intent);
        }

        multi_button.setOnClickListener {
            val intent: Intent = Intent(this, LevelActivity::class.java)
            intent.putExtra("mode","2 - Player")
            startActivity(intent);
        }

        back_button.setOnClickListener { finish() }

    }
}
