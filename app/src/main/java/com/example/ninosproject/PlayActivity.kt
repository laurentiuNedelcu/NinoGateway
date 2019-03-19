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

class PlayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_play)

        val tmp_win: Button = findViewById(R.id.tmpwinbutton)
        val tmp_lose: Button = findViewById(R.id.tmplosebutton)
        val pause: Button = findViewById(R.id.pauseButton)

        tmp_win.setOnClickListener {
            finsestraWin()
        }
        tmp_lose.setOnClickListener {
            finsestraLose()
        }
        pause.setOnClickListener {
            finsestraPause()
        }

    }


    val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt() // Metode per passar de density independent pixels (dp) a pixels

    private fun finsestraWin() {
        //Layout per fer visible el popup
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //Fem visible el layout però no el mostrem
        val view = inflater.inflate(R.layout.activity_you_win, null)

        //inicialitzem la finestra
        val popupWindow = PopupWindow(
            view, // Layout inflat que volem mostrar
            375.dp, // Width (dp transformat a pixel)
            335.dp, // Heigth (dp transformat a pixel)
            false
        )

        //mostrem la finestra amb el layout
        popupWindow.showAtLocation(findViewById(R.id.play_activity), Gravity.CENTER, 0, 0)

        var retry_level: Button = view.findViewById(R.id.retryButtonYW)
        var levels: Button = view.findViewById(R.id.levelsbuttonYW)
        var next_level: Button = view.findViewById(R.id.nextButtonYW)


        retry_level.setOnClickListener {
            popupWindow.dismiss()
        }


        levels.setOnClickListener {
            //val intent: Intent = Intent(this, LevelActivity::class.java)
            //startActivity(intent)
        }


        next_level.setOnClickListener {
            //val intent: Intent = Intent(this, seguent nivell)//falta per implementar
            //startActivity(intent)
        }


    }

    private fun finsestraLose() {
        //Layout per fer visible el popup
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //Fem visible el layout però no el mostrem
        val view = inflater.inflate(R.layout.activity_you_lose, null)

        //inicialitzem la finestra
        val popupWindow = PopupWindow(
            view, // Layout inflat que volem mostrar
            375.dp, // Width (dp transformat a pixel)
            335.dp, // Heigth (dp transformat a pixel)
            false
        )

        //mostrem la finestra amb el layout
        popupWindow.showAtLocation(findViewById(R.id.play_activity), Gravity.CENTER, 0, 0)

        var retry_level: Button = view.findViewById(R.id.retryButtonYL)
        retry_level.setOnClickListener {
            popupWindow.dismiss()
        }

        var levels: Button = view.findViewById(R.id.levelsButtonYL)
        levels.setOnClickListener {
            //val intent: Intent = Intent(this, LevelActivity::class.java)
            //startActivity(intent)
        }
    }

    private fun finsestraPause() {
        //Layout per fer visible el popup
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //Fem visible el layout però no el mostrem
        val view = inflater.inflate(R.layout.activity_pause, null)

        //inicialitzem la finestra
        val popupWindow = PopupWindow(
            view, // Layout inflat que volem mostrar
            375.dp, // Width (dp transformat a pixel)
            335.dp, // Heigth (dp transformat a pixel)
            false
        )

        //mostrem la finestra amb el layout
        popupWindow.showAtLocation(findViewById(R.id.play_activity), Gravity.CENTER, 0, 0)

        var repren_joc: Button = view.findViewById(R.id.repren_button)
        repren_joc.setOnClickListener {
            //Recuperar estat del joc i tornar a la partida.
            popupWindow.dismiss()
        }
        var opcions_joc: Button = view.findViewById(R.id.opcions_button)
        opcions_joc.setOnClickListener {
            //val intent : Intent = Intent(this, falta classe opcions)
            //startActivity(intent)
        }
        var menu_joc: Button = view.findViewById(R.id.menu_button)
        menu_joc.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent) //torna al menu principal, no al menu dels nivells
        }


    }
}
