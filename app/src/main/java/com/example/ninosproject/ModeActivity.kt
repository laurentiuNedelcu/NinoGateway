package com.example.ninosproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.Button

class ModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_mode_seleccion)

        val back_button : Button = findViewById<Button>(R.id.button_back)
        val single_button : Button = findViewById<Button>(R.id.button_singleplayer)
        val multi_button : Button = findViewById<Button>(R.id.button_multiplayer)

        val vibration: String = intent.getStringExtra("vibration")

        single_button.setOnClickListener {
            val intent: Intent = Intent(this, LevelActivity::class.java)
            intent.putExtra("mode",single_button.text.toString())
            intent.putExtra("vibration",vibration)
            startActivity(intent);
        }

        multi_button.setOnClickListener {
            val intent: Intent = Intent(this, LevelActivity::class.java)
            intent.putExtra("mode",single_button.text.toString())
            intent.putExtra("vibration",vibration)
            startActivity(intent);
        }

        back_button.setOnClickListener { finish() }

    }
}
