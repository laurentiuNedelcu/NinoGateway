package com.example.ninosproject.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import com.example.ninosproject.Data.AudioPlay
import com.example.ninosproject.R

class TutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_tutorial)

        val clickButton = AudioPlay.getSoundPool().load(this,R.raw.press_button,1)

        val back_button : Button = findViewById<Button>(R.id.button_back_tutorial)
        back_button.setOnClickListener {
            AudioPlay.getSoundPool().play(clickButton,1F,1F,0,0, 1F)
            finish()
        }

    }
}
