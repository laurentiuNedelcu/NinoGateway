package com.example.ninosproject.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.*
import com.example.ninosproject.Data.AudioPlay
import com.example.ninosproject.Data.LevelsArrays
import com.example.ninosproject.R

class LevelActivity : AppCompatActivity() {

    private var levelDraw: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_level_selector)

        AudioPlay.playMusic(this, R.raw.florian_bur_no_name, true)

        val clickButton = AudioPlay.getSoundPool().load(this, R.raw.press_button, 1)

        val text_mode: TextView = findViewById(R.id.textV_mode)
        val list_lvl: ListView = findViewById(R.id.lvl_selector)
        val button_back: Button = findViewById(R.id.button_back)

        if (intent.getStringExtra("mode") != null)
            text_mode.text = intent.getStringExtra("mode")

        val nivells = LevelsArrays.getLevelsUnlocked()
        val levelText = resources.getStringArray(R.array.levels)
        var i = 0
        while (i < 10) {
            if (nivells[i] == "1")
                levelDraw.add(levelText[i])
            else
                levelDraw.add("Locked")
            i++
        }
        val nivellList: ArrayAdapter<String> = ArrayAdapter(
            this,
            R.layout.item_list_background, levelDraw
        )

        list_lvl.adapter = nivellList
        list_lvl.setOnItemClickListener { parent, view, position, id ->
            AudioPlay.getSoundPool().play(clickButton, 1F, 1F, 0, 0, 1F)
            AudioPlay.stopMusic()
            if (levelDraw[position] == "Locked")
                Toast.makeText(this, "Desbloquea los otros niveles primero", Toast.LENGTH_SHORT).show()
            else {
                val intent = Intent(this, PlayActivity::class.java)
                intent.putExtra("level", position.toString())
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }

        button_back.setOnClickListener {
            AudioPlay.getSoundPool().play(clickButton, 1F, 1F, 0, 0, 1F)
            finish()
        }

    }
}