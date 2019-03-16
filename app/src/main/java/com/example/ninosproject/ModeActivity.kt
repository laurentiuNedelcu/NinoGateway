package com.example.ninosproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mode_seleccion)
        val back_button : Button = findViewById<Button>(R.id.button_back)

        back_button.setOnClickListener { finish() }
    }
}
