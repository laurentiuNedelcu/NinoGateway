package com.example.ninosproject.Activities

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.TextView
import com.example.ninosproject.R

class TutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_show_tutorial)

        val playerInfo = findViewById<ConstraintLayout>(R.id.PlayerInfo)
        val objectInfo = findViewById<ConstraintLayout>(R.id.ObjectInfo)
        val trapInfo = findViewById<ConstraintLayout>(R.id.TrapInfo)
        val obstacleInfo = findViewById<ConstraintLayout>(R.id.ObstacleInfo)

        playerInfo.setOnClickListener {
            popup("player")
        }
        objectInfo.setOnClickListener {
            popup("objectes")
        }
        trapInfo.setOnClickListener {
            popup("trampes")
        }
        obstacleInfo.setOnClickListener {
            popup("obstacles")
        }
        /*
        val clickButton = AudioPlay.getSoundPool().load(this,R.raw.press_button,1)

        val back_button : Button = findViewById<Button>(R.id.button_back_tutorial)
        back_button.setOnClickListener {
            AudioPlay.getSoundPool().play(clickButton,1F,1F,0,0, 1F)
            finish()
        }
        */

    }

    val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt() // Metode per passar de density independent pixels (dp) a pixels

    fun popup(info: String) {
        //A partir d'aqui generem la finestra popup on es trobarán totes les opcions

        //Layout per fer visible el popup
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //Fem visible el layout però no el mostrem
        val view = inflater.inflate(R.layout.popup_info, null)

        //inicialitzem la finestra
        val popupWindow = PopupWindow(
            view, // Layout inflat que volem mostrar
            500.dp, // Width (dp transformat a pixel)
            300.dp, // Heigth (dp transformat a pixel)
            true // Si cliquem fora de la finestra, es tancarà
        )

        //mostrem la finestra amb el layout
        popupWindow.showAtLocation(findViewById(R.id.ShowInfo), Gravity.CENTER, 0, 0)

        val showInfo: TextView = view.findViewById(R.id.GameInfo)


        when(info){
            "player"->{
                showInfo.text = getString(R.string.info_player)
            }
            "trampes"->{
                showInfo.text = getString(R.string.info_traps)
            }
            "obstacles"->{
                showInfo.text = getString(R.string.info_obs)
            }
            "objectes"->{
                showInfo.text = getString(R.string.info_obj)
            }
        }
    }
}
