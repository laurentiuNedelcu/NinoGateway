package com.example.ninosproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_log_in.view.*

class LogInActivity : AppCompatActivity() {

    private lateinit var logInButton: Button
    private lateinit var guestButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        logInButton = findViewById(R.id.logIn_id)
        guestButton = findViewById(R.id.guest_id)

        logInButton.setOnClickListener { Toast.makeText(this,"La funció encara no està implementada",Toast.LENGTH_SHORT).show() }
        guestButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}
