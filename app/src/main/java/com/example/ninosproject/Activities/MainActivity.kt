package com.example.ninosproject.Activities

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
import com.example.ninosproject.Data.AudioPlay
import com.example.ninosproject.R


class MainActivity : AppCompatActivity() {

    private lateinit var button_jugar: Button
    private lateinit var button_exit: Button
    private lateinit var button_options: Button
    private lateinit var sfx: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main_menu)

        AudioPlay.playMusic(this, R.raw.sm, true) //PONED LA CANCION QUE QUERAIS PARA EL MENU
        AudioPlay.enableSFX()

        val clickButton = AudioPlay.getSoundPool().load(this, R.raw.press_button, 1)
        val clickOptions = AudioPlay.getSoundPool().load(this, R.raw.pause_button, 1)

        button_jugar = findViewById(R.id.jugar)
        button_exit = findViewById(R.id.button_exit)
        button_options = findViewById(R.id.button_options)

        sfx = if (intent.getStringExtra("sfx") != null) intent.getStringExtra("sfx")
        else getString(R.string.on)

        button_jugar.setOnClickListener {
            AudioPlay.getSoundPool().play(clickButton, 1F, 1F, 0, 0, 1F)
            val intent = Intent(this, ModeActivity::class.java)
            intent.putExtra("sfx", sfx)
            startActivity(intent)
        }

        button_exit.setOnClickListener {
            AudioPlay.getSoundPool().play(clickButton, 1F, 1F, 0, 0, 1F)
            AudioPlay.stopMusic()
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            finish()
            startActivity(intent)
        }

        button_options.setOnClickListener {
            AudioPlay.getSoundPool().play(clickOptions, 1F, 1F, 0, 0, 1F)
            popup(clickButton)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AudioPlay.getSoundPool().release()
    }

    val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt() // Metode per passar de density independent pixels (dp) a pixels

    fun popup(clickButton: Int) {
        //A partir d'aqui generem la finestra popup on es trobarán totes les opcions

        //Layout per fer visible el popup
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //Fem visible el layout però no el mostrem
        val view = inflater.inflate(R.layout.popup_option_menu, null)

        //inicialitzem la finestra
        val popupWindow = PopupWindow(
            view, // Layout inflat que volem mostrar
            350.dp, // Width (dp transformat a pixel)
            300.dp, // Heigth (dp transformat a pixel)
            true // Si cliquem fora de la finestra, es tancarà
        )

        //mostrem la finestra amb el layout
        popupWindow.showAtLocation(findViewById(R.id.main_menu), Gravity.CENTER, 0, 0)

        val buttonSfx: Button = view.findViewById(R.id.button_sfx)
        val button_accept: Button = view.findViewById(R.id.button_accept)
        val button_deny: Button = view.findViewById(R.id.button_deny)

        buttonSfx.text = sfx

        buttonSfx.setOnClickListener { sfx(buttonSfx) }

        button_accept.setOnClickListener {
            AudioPlay.getSoundPool().play(clickButton, 1F, 1F, 0, 0, 1F)
            sfx = buttonSfx.text.toString(); popupWindow.dismiss()
        }

        button_deny.setOnClickListener {
            AudioPlay.getSoundPool().play(clickButton, 1F, 1F, 0, 0, 1F)
            popupWindow.dismiss()
        }
    }

    private fun sfx(button: Button) {
        runOnUiThread(
            object : Runnable {
                override fun run() {
                    if (button.text.equals(getString(R.string.on))) {
                        button.text = getString(R.string.off)
                        AudioPlay.disableSFX()
                    } else {
                        synchronized(AudioPlay.getSoundPool()) {
                            button.text = getString(R.string.on)
                            AudioPlay.enableSFX()
                        }
                    }
                }
            })
    }

    override fun onBackPressed() {} //Deshabilitar back button del mobil
}
