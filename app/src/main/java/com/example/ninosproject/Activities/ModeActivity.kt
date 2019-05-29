@file:Suppress("DEPRECATION")

package com.example.ninosproject.Activities
import com.example.ninosproject.Activities.MultiPlayerActivities.ServerActivity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import com.example.ninosproject.Data.AudioPlay
import com.example.ninosproject.R

class ModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_mode_seleccion)

        val clickButton = AudioPlay.getSoundPool().load(this,R.raw.press_button,1)

        val back_button : Button = findViewById<Button>(R.id.button_back)
        val single_button : Button = findViewById<Button>(R.id.button_singleplayer)
        val multi_button : Button = findViewById<Button>(R.id.button_multiplayer)

        val sfx: String = intent.getStringExtra("sfx")

        single_button.setOnClickListener {
            AudioPlay.getSoundPool().play(clickButton,1F,1F,0,0, 1F)
            val intent: Intent = Intent(this, LevelActivity::class.java)
            intent.putExtra("mode",single_button.text.toString())
            intent.putExtra("sfx",sfx)
            startActivity(intent)
        }

        multi_button.setOnClickListener {
            AudioPlay.getSoundPool().play(clickButton,1F,1F,0,0, 1F)
            //val intent: Intent = Intent(this, LevelActivity::class.java)
            val intent: Intent = Intent(this, ServerActivity::class.java)
            intent.putExtra("mode",multi_button.text.toString())
            intent.putExtra("sfx",sfx)
            startActivity(intent)
        }

        back_button.setOnClickListener {
            AudioPlay.getSoundPool().play(clickButton,1F,1F,0,0, 1F)
            finish()
        }

    }
}
