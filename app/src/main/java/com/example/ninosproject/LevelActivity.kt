package com.example.ninosproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class LevelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_selector)

        val text_mode : TextView = findViewById(R.id.textV_mode)
        val list_lvl : ListView = findViewById(R.id.lvl_selector)
        val button_back : Button = findViewById(R.id.button_back)

        if (intent.getStringExtra("mode")!= null)
            text_mode.text = intent.getStringExtra("mode")

        val nivells = arrayOf("Nivell 1 - Molt Facil","Nivell 2 - Molt Facil","Nivell 3 - Facil","Nivell 4 - Facil","Nivell 5 - Intermitg","Nivell 6 - Intermitg","Nivell 7 - Dificil","Nivell 8 - Dificil","Nivell 9 - Molt Dificil","Nivell 10 - Molt Dificil")
        val nivellList: ArrayAdapter<String> = ArrayAdapter(this,R.layout.item_list_background,nivells)

        list_lvl.adapter = nivellList

        button_back.setOnClickListener {
            finish()
        }
    }




}
