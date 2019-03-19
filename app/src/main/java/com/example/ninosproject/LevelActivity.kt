package com.example.ninosproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class LevelActivity : AppCompatActivity() {

    private var idioma: Int = 1

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

        val intent = intent
        val aux = intent.getStringExtra("idioma")
        when {
            aux.equals("català") -> idioma = 1
            aux.equals("english") -> idioma = 2
            aux.equals("espanol") -> idioma = 3
        }

        val nivells = arrayOf("Nivell 1 - Molt Fàcil","Nivell 2 - Molt Fàcil","Nivell 3 - Fàcil","Nivell 4 - Fàcil","Nivell 5 - Intermitg","Nivell 6 - Intermitg","Nivell 7 - Difícil","Nivell 8 - Difícil","Nivell 9 - Molt Difícil","Nivell 10 - Molt Difícil")
        val nivellList: ArrayAdapter<String> = ArrayAdapter(this,R.layout.item_list_background,nivells)

        val niveles = arrayOf("Nivel 1 - Muy Fácil","Nivel 2 - Muy Fácil","Nivel 3 - Fácil","Nivel 4 - Fácil","Nivel 5 - Intermedio","Nivel 6 - Intermedio","Nivel 7 - Difícil","Nivel 8 - Difícil","Nivel 9 - Muy Difícil","Nivel 10 - Muy Difícil")
        val nivelList: ArrayAdapter<String> = ArrayAdapter(this,R.layout.item_list_background,niveles)

        val levels = arrayOf("Level 1 - Very Easy","Level 2 - Very Easy","Level 3 - Easy","Level 4 - Easy","Level 5 - Normal","Level 6 - Normal","Level 7 - Hard","Level 8 - Hard","Level 9 - Very Hard","Level 10 - Very Hard")
        val levelList: ArrayAdapter<String> = ArrayAdapter(this,R.layout.item_list_background,levels)

        if (idioma == 1) {
            list_lvl.adapter = nivellList
            list_lvl.setOnItemClickListener { parent, view, position, id ->
                val intent: Intent = Intent(this, PlayActivity::class.java)
                startActivity(intent)
            }
        }
        if (idioma == 2) {
            list_lvl.adapter = levelList
            list_lvl.setOnItemClickListener { parent, view, position, id ->
                val intent: Intent = Intent(this, PlayActivity::class.java)
                startActivity(intent)
            }
        }
        if (idioma == 3) {
            list_lvl.adapter = nivelList
            list_lvl.setOnItemClickListener { parent, view, position, id ->
                val intent: Intent = Intent(this, PlayActivity::class.java)
                startActivity(intent)
            }
        }

        button_back.setOnClickListener {
            finish()
        }

    }
}