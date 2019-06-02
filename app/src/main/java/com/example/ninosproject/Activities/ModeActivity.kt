package com.example.ninosproject.Activities

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

        val clickButton = R.raw.press_button

        val back_button: Button = findViewById(R.id.button_back)
        val single_button: Button = findViewById(R.id.button_singleplayer)
        val tutorial_button: Button = findViewById(R.id.button_tutorial)

        single_button.setOnClickListener {
            AudioPlay.playSfx(this, clickButton)
            val intent = Intent(this, LevelActivity::class.java)
            intent.putExtra("mode", single_button.text.toString())
            startActivity(intent)
        }


        back_button.setOnClickListener {
            AudioPlay.playSfx(this, clickButton)
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
        tutorial_button.setOnClickListener {
            AudioPlay.playSfx(this, clickButton)
            val intent = Intent(this, TutorialActivity::class.java)
            intent.putExtra("mode", tutorial_button.text.toString())
            startActivity(intent)
        }

    }
}
