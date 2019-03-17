package com.example.ninosproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    lateinit var button_jugar : Button
    lateinit var button_exit : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        button_jugar = findViewById<Button>(R.id.jugar)
        button_exit = findViewById<Button>(R.id.button_exit)


        button_jugar.setOnClickListener {
            val intent: Intent = Intent(this, ModeActivity::class.java)
            startActivity(intent);
        }

        button_exit.setOnClickListener {
            finish()
        }
    }
}
