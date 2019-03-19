package com.example.ninosproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.Button

class PauseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_pause)

        val repren_joc: Button = findViewById(R.id.repren_button)
        repren_joc.setOnClickListener {
            //Recuperar estat del joc i tornar a la partida.
            //Falta activity de la partida
        }
        val opcions_joc: Button = findViewById(R.id.opcions_button)
        opcions_joc.setOnClickListener {
            //val intent : Intent = Intent(this, falta classe opcions)
            //startActivity(intent)
        }
        val menu_joc: Button = findViewById(R.id.menu_button)
        menu_joc.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent) //torna al menu principal, no al menu dels nivells
        }
    }
}
