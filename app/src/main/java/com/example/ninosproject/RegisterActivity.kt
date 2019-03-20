package com.example.ninosproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    private lateinit var backButton: Button
    private lateinit var validateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_register)

        backButton = findViewById(R.id.button_back)
        validateButton = findViewById(R.id.button_validate)

        backButton.setOnClickListener { finish() }
        validateButton.setOnClickListener { Toast.makeText(this, "La funció encara no està implementada", Toast.LENGTH_SHORT).show() }
    }
}
