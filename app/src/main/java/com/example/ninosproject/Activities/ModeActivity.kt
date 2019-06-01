package com.example.ninosproject.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import com.example.ninosproject.Activities.MultiPlayerActivities.ServerActivity
import com.example.ninosproject.Data.AudioPlay
import com.example.ninosproject.R

class ModeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_mode_seleccion)

        val clickButton = AudioPlay.getSoundPool().load(this, R.raw.press_button, 1)

        val back_button: Button = findViewById(R.id.button_back)
        val single_button: Button = findViewById(R.id.button_singleplayer)
        val multi_button: Button = findViewById(R.id.button_multiplayer)
        val tutorial_button: Button = findViewById(R.id.button_tutorial)

        single_button.setOnClickListener {
            AudioPlay.getSoundPool().play(clickButton, 1F, 1F, 0, 0, 1F)
            val intent = Intent(this, LevelActivity::class.java)
            intent.putExtra("mode", single_button.text.toString())
            startActivity(intent)
        }

        multi_button.setOnClickListener {
            AudioPlay.getSoundPool().play(clickButton, 1F, 1F, 0, 0, 1F)
            //val intent: Intent = Intent(this, LevelActivity::class.java)
            val intent = Intent(this, ServerActivity::class.java)
            intent.putExtra("mode", multi_button.text.toString())
            startActivity(intent)
        }

        back_button.setOnClickListener {
            AudioPlay.getSoundPool().play(clickButton, 1F, 1F, 0, 0, 1F)
            finish()
        }
        tutorial_button.setOnClickListener {
            AudioPlay.getSoundPool().play(clickButton, 1F, 1F, 0, 0, 1F)
            val intent = Intent(this, TutorialActivity::class.java)
            intent.putExtra("mode", tutorial_button.text.toString())
            startActivity(intent)
        }

    }
}
