package com.example.ninosproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView

class LanguageActivity : AppCompatActivity() {

    private lateinit var selectLanguage: TextView
    private lateinit var catalaButton: Button
    private lateinit var englishButton: Button
    private lateinit var espanolButton: Button
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_language)

        selectLanguage = findViewById(R.id.selectLanguage)
        catalaButton = findViewById(R.id.catalaButton)
        englishButton = findViewById(R.id.englishButton)
        espanolButton = findViewById(R.id.espanyolButton)
        cancelButton = findViewById(R.id.button_cancel)

        val intent = intent
        val aux = intent.getStringExtra("idioma")
        when (aux) {
            "català" -> {
                selectLanguage.text = "Seleccioneu un idioma"
                cancelButton.text = "Cancel·lar"
            }
            "english" -> {
                selectLanguage.text = "Select a language"
                cancelButton.text = "Cancel"
            }
            "espanol" -> {
                selectLanguage.text = "Seleccione un idioma"
                cancelButton.text = "Cancelar"
            }
        }

        cancelButton.setOnClickListener { finish() }
        catalaButton.setOnClickListener { canviarIdioma() }
        englishButton.setOnClickListener { changeLanguage() }
        espanolButton.setOnClickListener { cambiarIdioma() }
    }

    private fun cambiarIdioma() {
        intent = Intent(this, MainActivity::class.java)
        intent.putExtra("idioma", "espanol")
        setResult(1, intent)
        finish()
    }

    private fun changeLanguage() {
        intent = Intent(this, MainActivity::class.java)
        intent.putExtra("idioma", "english")
        setResult(1, intent)
        finish()
    }

    private fun canviarIdioma() {
        intent = Intent(this, MainActivity::class.java)
        intent.putExtra("idioma", "català")
        setResult(1, intent)
        finish()
    }
}
