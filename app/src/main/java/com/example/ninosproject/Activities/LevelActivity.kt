package com.example.ninosproject.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.ninosproject.R

class LevelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_level_selector)

        val text_mode : TextView = findViewById(R.id.textV_mode)
        val list_lvl : ListView = findViewById(R.id.lvl_selector)
        val button_back : Button = findViewById(R.id.button_back)

        if (intent.getStringExtra("mode")!= null)
            text_mode.text = intent.getStringExtra("mode")

        val vibration: String = intent.getStringExtra("vibration")

        val nivells = resources.getStringArray(R.array.levels)
        val nivellList: ArrayAdapter<String> = ArrayAdapter(this,
            R.layout.item_list_background,nivells)

        list_lvl.adapter = nivellList
        list_lvl.setOnItemClickListener { parent, view, position, id ->
            val intent: Intent = Intent(this, PlayActivity::class.java)
            intent.putExtra("vibration",vibration)
            startActivity(intent)
        }

        button_back.setOnClickListener {
            finish()
        }

    }
}