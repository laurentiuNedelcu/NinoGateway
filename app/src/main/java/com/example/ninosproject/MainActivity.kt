package com.example.ninosproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        var button_jugar : Button = findViewById<Button>(R.id.jugar)
        var button_exit : Button = findViewById<Button>(R.id.button_exit)

        button_jugar.setOnClickListener {
            val intent: Intent = Intent(this, ModeActivity::class.java)
            startActivity(intent);
        }

        button_exit.setOnClickListener {
            finish()
        }
    }
}
