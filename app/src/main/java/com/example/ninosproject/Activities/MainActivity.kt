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
import com.example.ninosproject.Data.Firebase
import com.example.ninosproject.Data.Guest
import com.example.ninosproject.Data.LevelsArrays
import com.example.ninosproject.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {

    private lateinit var button_jugar: Button
    private lateinit var button_exit: Button
    private lateinit var button_options: Button
    private lateinit var log_out: Button
    private lateinit var sfx: String
    private lateinit var musica: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main_menu)

        LevelsArrays.initList()

        AudioPlay.getStrings(getString(R.string.on), getString(R.string.off))
        getSfxMusica()
        AudioPlay.playMusic(this, R.raw.florian_bur_no_name, true)

        val clickButton = R.raw.press_button
        val clickOptions = R.raw.pause_button

        button_jugar = findViewById(R.id.jugar)
        button_exit = findViewById(R.id.button_exit)
        button_options = findViewById(R.id.button_options)
        log_out = findViewById(R.id.log_out_button)

        button_jugar.setOnClickListener {
            AudioPlay.playSfx(this, clickButton)
            val intent = Intent(this, ModeActivity::class.java)
            finish()
            startActivity(intent)
        }

        button_exit.setOnClickListener {
            AudioPlay.playSfx(this, clickButton)
            AudioPlay.stopMusic()
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            finish()
            startActivity(intent)
        }

        button_options.setOnClickListener {
            AudioPlay.playSfx(this, clickOptions)
            popup(clickButton)
        }

        log_out.setOnClickListener {
            AudioPlay.playSfx(this, clickButton)
            AudioPlay.stopMusic()
            LevelsArrays.resetList()
            Firebase.performLogOut()
            val intent = Intent(this, LogInActivity::class.java)
            finish()
            startActivity(intent)
        }
    }

    private fun getSfxMusica() {
        if (Guest.getGuest()) {
            if (AudioPlay.getSFX() != "" && AudioPlay.getMusica() != "") {
                sfx = AudioPlay.getSFX()
                musica = AudioPlay.getMusica()
            } else {
                sfx = getString(R.string.on)
                musica = getString(R.string.on)
            }
        } else {
            sfx = AudioPlay.getSFX()
            musica = AudioPlay.getMusica()
        }
        AudioPlay.setSFXValue(sfx)
        AudioPlay.setMusicaValue(musica)
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
        val buttonMusic: Button = view.findViewById(R.id.button_musica)
        val button_accept: Button = view.findViewById(R.id.button_accept)
        val button_deny: Button = view.findViewById(R.id.button_deny)

        buttonSfx.text = sfx
        buttonMusic.text = musica

        buttonSfx.setOnClickListener { onOff(buttonSfx) }
        buttonMusic.setOnClickListener { onOff(buttonMusic) }

        button_accept.setOnClickListener {
            sfx = buttonSfx.text.toString()
            musica = buttonMusic.text.toString()
            AudioPlay.setSFXValue(sfx)
            AudioPlay.setMusicaValue(musica)
            AudioPlay.updateMusica()
            if (sfx == AudioPlay.on)
                AudioPlay.playSfx(this, clickButton)
            if (!Guest.getGuest()) {
                search(Firebase.getAuth().uid.toString(), 2, "sfx")
                search(Firebase.getAuth().uid.toString(), 2, "music")
            }
            popupWindow.dismiss()
        }

        button_deny.setOnClickListener {
            AudioPlay.playSfx(this, clickButton)
            popupWindow.dismiss()
        }
    }

    private fun search(key: String, action: Int, audio: String) {
        Firebase.getReferenceUser().addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (action == 1) {
                    Firebase.getReferenceUser().child(key).child("Audio").child(audio).addListenerForSingleValueEvent(
                        object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                if (audio == "sfx")
                                    sfx = dataSnapshot.value.toString()
                                else
                                    musica = dataSnapshot.value.toString()
                            }
                        }
                    )
                } else if (action == 2) {
                    if (audio == "sfx")
                        Firebase.getReferenceUser().child(key).child("Audio").child(audio).setValue(sfx)
                    else
                        Firebase.getReferenceUser().child(key).child("Audio").child(audio).setValue(musica)
                }
            }
        })
    }

    private fun onOff(button: Button) {
        runOnUiThread {
            if (button.text == getString(R.string.on)) {
                button.text = getString(R.string.off)
            } else {
                button.text = getString(R.string.on)
            }
        }
    }

    override fun onBackPressed() {} //Deshabilitar back button del mobil
}
