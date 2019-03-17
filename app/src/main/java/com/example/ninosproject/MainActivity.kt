package com.example.ninosproject

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import android.widget.PopupWindow



class MainActivity : AppCompatActivity() {

    lateinit var button_jugar : Button
    lateinit var button_exit : Button
    lateinit var button_options : Button
    var vibration_state : String = "Activat"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        button_jugar = findViewById<Button>(R.id.jugar)
        button_exit = findViewById<Button>(R.id.button_exit)
        button_options = findViewById<Button>(R.id.button_options)


        button_jugar.setOnClickListener {
            val intent: Intent = Intent(this, ModeActivity::class.java)
            startActivity(intent);
        }

        button_exit.setOnClickListener {
            finish()
        }

        button_options.setOnClickListener {
            popup()

        }
    }

    val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt() // Metode per passar de density independent pixels (dp) a pixels

    fun popup(){
        //A partir d'aqui generem la finestra popup on es trobarán totes les opcions

        //Layout per fer visible el popup
        val inflater : LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //Fem visible el layout però no el mostrem
        val view = inflater.inflate(R.layout.popup_option_menu,null)

        //inicialitzem la finestra
        val popupWindow = PopupWindow(
            view, // Layout inflat que volem mostrar
            350.dp, // Width (dp transformat a pixel)
            300.dp, // Heigth (dp transformat a pixel)
            true // Si cliquem fora de la finestra, es tancarà
        )

        //mostrem la finestra amb el layout
        popupWindow.showAtLocation(findViewById(R.id.main_menu),Gravity.CENTER,0,0)

        var bar_volum : SeekBar = view.findViewById(R.id.bar_volum)
        var button_vibracio : Button = view.findViewById(R.id.button_vibracio)
        var button_accept : Button = view.findViewById(R.id.button_accept)
        var button_deny : Button = view.findViewById(R.id.button_deny)

        button_vibracio.text = vibration_state

        var estat_anterior = vibration_state
        button_vibracio.setOnClickListener { vibracio(button_vibracio) }

        button_accept.setOnClickListener { vibration_state = button_vibracio.text.toString(); popupWindow.dismiss() }
        button_deny.setOnClickListener { popupWindow.dismiss() }
    }

    fun vibracio(button: Button){
        if(button.text.equals("Activat"))
            button.text = "Descativat"
        else
            button.text = "Activat"
    }
}
