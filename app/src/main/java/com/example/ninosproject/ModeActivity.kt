package com.example.ninosproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.Button

class ModeActivity : AppCompatActivity() {

    private var idioma: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_mode_seleccion)
        val back_button : Button = findViewById<Button>(R.id.button_back)
        val single_button : Button = findViewById<Button>(R.id.button_singleplayer)
        val multi_button : Button = findViewById<Button>(R.id.button_multiplayer)

        val intent = intent
        val aux = intent.getStringExtra("idioma")
        when {
            aux.equals("català") -> idioma = 1
            aux.equals("english") -> idioma = 2
            aux.equals("espanol") -> idioma = 3
        }
        actualitzarIdioma(single_button, multi_button)

        single_button.setOnClickListener {
            val intent: Intent = Intent(this, LevelActivity::class.java)
            intent.putExtra("mode",single_button.text.toString())
            intent.putExtra("idioma",idiomaEnUs())
            startActivity(intent);
        }

        multi_button.setOnClickListener {
            val intent: Intent = Intent(this, LevelActivity::class.java)
            intent.putExtra("mode",multi_button.text.toString())
            intent.putExtra("idioma",idiomaEnUs())
            startActivity(intent);
        }

        back_button.setOnClickListener { finish() }

    }

    private fun actualitzarIdioma(single: Button, multi: Button) {
        if (idioma == 1){
            single.text = "1 - Jugador"
            multi.text = "2 - Jugadors"
        } else if (idioma == 2){
            single.text = "1 - Player"
            multi.text = "2 - Players"
        } else if (idioma == 3){
            single.text = "1 - Jugador"
            multi.text = "2 - Jugadores"
        }
    }

    private fun idiomaEnUs(): String {
        if (idioma == 1) return "català" else if (idioma == 2) return "english" else return "espanol"
    }
}
