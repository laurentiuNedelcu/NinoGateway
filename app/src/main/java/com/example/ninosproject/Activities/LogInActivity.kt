package com.example.ninosproject.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.example.ninosproject.R

class LogInActivity : AppCompatActivity() {

    private lateinit var logInButton: Button
    private lateinit var registerButton: Button
    private lateinit var guestButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_log_in)

        logInButton = findViewById(R.id.logIn_id)
        registerButton = findViewById(R.id.register_id)
        guestButton = findViewById(R.id.guest_id)

        logInButton.setOnClickListener { Toast.makeText(this,getString(R.string.function),Toast.LENGTH_SHORT).show() }
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        guestButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
