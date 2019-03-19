package com.example.ninosproject

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.PopupWindow
import android.widget.SeekBar
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    lateinit var button_jugar: Button
    lateinit var button_exit: Button
    lateinit var button_options: Button
    var vibration_state: String = "Activat"

    private var idioma: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main_menu)

        button_jugar = findViewById<Button>(R.id.jugar)
        button_exit = findViewById<Button>(R.id.button_exit)
        button_options = findViewById<Button>(R.id.button_options)

        actualitzarIdiomaMenu(button_jugar, button_options, button_exit)

        button_jugar.setOnClickListener {
            val intent: Intent = Intent(this, ModeActivity::class.java)
            intent.putExtra("idioma", idiomaEnUs())
            startActivity(intent)
        }

        button_exit.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        button_options.setOnClickListener {
            popup()

        }
    }

    private fun idiomaEnUs(): String {
        if (idioma == 1) return "català" else if (idioma == 2) return "english" else return "espanol"
    }

    private fun actualitzarIdiomaMenu(jugar: Button, opcions: Button, sortir: Button) {
        when (idioma) {
            1 -> {
                jugar.text = "Jugar"
                opcions.text = "Opcions"
                sortir.text = "Sortir"
            }
            2 -> {
                jugar.text = "Play"
                opcions.text = "Options"
                sortir.text = "Exit"
            }
            3 -> {
                jugar.text = "Jugar"
                opcions.text = "Opciones"
                sortir.text = "Salir"
            }
        }
    }

    val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt() // Metode per passar de density independent pixels (dp) a pixels

    fun popup() {
        //A partir d'aqui generem la finestra popup on es trobarán totes les opcions

        //Layout per fer visible el popup
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //Fem visible el layout però no el mostrem
        val view = inflater.inflate(R.layout.popup_option_menu, null)

        //inicialitzem la finestra
        val popupWindow = PopupWindow(
            view, // Layout inflat que volem mostrar
            350.dp, // Width (dp transformat a pixel)
            335.dp, // Heigth (dp transformat a pixel)
            true // Si cliquem fora de la finestra, es tancarà
        )

        //mostrem la finestra amb el layout
        popupWindow.showAtLocation(findViewById(R.id.main_menu), Gravity.CENTER, 0, 0)

        var bar_volum: SeekBar = view.findViewById(R.id.bar_volum)
        var game_version: TextView = view.findViewById(R.id.game_version)
        var button_vibracio: Button = view.findViewById(R.id.button_vibracio)
        var button_accept: Button = view.findViewById(R.id.button_accept)
        var button_deny: Button = view.findViewById(R.id.button_deny)
        var button_idioma: Button = view.findViewById(R.id.button_idioma)
        var volumView: TextView = view.findViewById(R.id.volumView)
        var vibracioView: TextView = view.findViewById(R.id.vibracioView)
        var idiomaView: TextView = view.findViewById(R.id.languageView)

        actualitzarIdiomaPopup(button_accept, button_deny, button_idioma, volumView, vibracioView, idiomaView, game_version)
        button_vibracio.text = vibration_state

        var estat_anterior = vibration_state
        button_vibracio.setOnClickListener { vibracio(button_vibracio) }

        button_accept.setOnClickListener { vibration_state = button_vibracio.text.toString(); popupWindow.dismiss() }
        button_deny.setOnClickListener { popupWindow.dismiss() }
        button_idioma.setOnClickListener { canviarIdioma(popupWindow) }
    }

    private fun actualitzarIdiomaPopup(accept: Button, deny: Button, bidioma: Button, volumView: TextView, vibracioView: TextView,
        idiomaView: TextView, game_version: TextView) {
        when (idioma) {
            1 -> {
                game_version.text = "Versió del Joc: 0.x.x"
                accept.text = "Acceptar"
                deny.text = "Cancel·lar"
                bidioma.text = "Català"
                volumView.text = "Volum:"
                vibracioView.text = "Vibració:"
                idiomaView.text = "Idioma:"
                if (vibration_state.equals("On") || vibration_state.equals("Activado"))
                    vibration_state = "Activat"
                else if (vibration_state.equals("Off") || vibration_state.equals("Desactivado"))
                    vibration_state = "Desactivat"
            }
            2 -> {
                game_version.text = "Game Version: 0.x.x"
                accept.text = "Accept"
                deny.text = "Cancel"
                bidioma.text = "English"
                volumView.text = "Volume:"
                vibracioView.text = "Vibration:"
                idiomaView.text = "Language:"
                if (vibration_state.equals("Activat") || vibration_state.equals("Activado"))
                    vibration_state = "On"
                else if (vibration_state.equals("Desactivat") || vibration_state.equals("Desactivado"))
                    vibration_state = "Off"
            }
            3 -> {
                game_version.text = "Versión del Juego: 0.x.x"
                accept.text = "Aceptar"
                deny.text = "Cancelar"
                bidioma.text = "Español"
                volumView.text = "Volumen:"
                vibracioView.text = "Vibración:"
                idiomaView.text = "Lenguaje:"
                if (vibration_state.equals("Activat") || vibration_state.equals("On"))
                    vibration_state = "Activado"
                else if (vibration_state.equals("Desactivat") || vibration_state.equals("Off"))
                    vibration_state = "Desactivado"
            }
        }
    }

    private fun canviarIdioma(popupWindow: PopupWindow) {
        val intent = Intent(this, LanguageActivity::class.java)
        var aux: String = "català"
        if (idioma == 2) aux = "english"
        if (idioma == 3) aux = "espanol"
        intent.putExtra("idioma", aux)
        startActivityForResult(intent, 2)
        popupWindow.dismiss()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 1) {
            val aux = data?.getStringExtra("idioma")
            when {
                aux.equals("català") -> idioma = 1
                aux.equals("english") -> idioma = 2
                aux.equals("espanol") -> idioma = 3
            }
        }
        actualitzarIdiomaMenu(button_jugar, button_options, button_exit)
        popup()
    }

    fun vibracio(button: Button) {
        if (button.text.equals("Activat")) button.text = "Desactivat"
        else if (button.text.equals("Desactivat")) button.text = "Activat"

        if (button.text.equals("On")) button.text = "Off"
        else if (button.text.equals("Off")) button.text = "On"

        if (button.text.equals("Activado")) button.text = "Desactivado"
        else if (button.text.equals("Desactivado")) button.text = "Activado"
    }

    override fun onBackPressed() {} //Deshabilitar back button del mobil
}
