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

        var text_mode : TextView = findViewById<TextView>(R.id.textV_mode)
        var list_lvl : ListView = findViewById<ListView>(R.id.lvl_selector)
        var button_back : Button = findViewById<Button>(R.id.button_back)

        if (getIntent().getStringExtra("mode")!= null)
            text_mode.text = getIntent().getStringExtra("mode")

        var nivells = arrayOf("Nivell 1 - Molt Facil","Nivell 2 - Molt Facil","Nivell 3 - Facil","Nivell 4 - Facil","Nivell 5 - Intermitg","Nivell 6 - Intermitg","Nivell 7 - Dificil","Nivell 8 - Dificil","Nivell 9 - Molt Dificil","Nivell 10 - Molt Dificil")
        var nivellList: ArrayAdapter<String> = ArrayAdapter(this,R.layout.item_list_background,nivells)

        list_lvl.adapter = nivellList

        button_back.setOnClickListener {
            finish()
        }
    }




}
