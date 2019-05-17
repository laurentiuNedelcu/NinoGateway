package com.example.ninosproject.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.ninosproject.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RegisterActivity : AppCompatActivity() {

    private lateinit var backButton: Button
    private lateinit var validateButton: Button
    private lateinit var userNameText: TextView
    private lateinit var emailText: TextView
    private lateinit var passwordText: TextView
    private lateinit var confirmPasswordText: TextView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var dbReference: DatabaseReference
    private lateinit var dataBase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()
        dataBase = FirebaseDatabase.getInstance()
        dbReference = dataBase.reference.child("User")

        backButton = findViewById(R.id.button_back)
        validateButton = findViewById(R.id.button_validate)
        userNameText = findViewById(R.id.userNameText)
        emailText = findViewById(R.id.emailText)
        passwordText = findViewById(R.id.passwordText)
        confirmPasswordText = findViewById(R.id.confirmPasswordText)

        backButton.setOnClickListener { finish() }
        validateButton.setOnClickListener { register() }
    }

    fun register() {
        val userName: String = userNameText.text.toString().trim()
        val password: String = passwordText.text.toString().trim()
        val email: String = emailText.text.toString().trim()
        val confirmPassword: String = confirmPasswordText.text.toString().trim()

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, getString(R.string.Ingresar_username), Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, getString(R.string.Ingresar_email), Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, getString(R.string.Ingresar_password), Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, getString(R.string.retry_password), Toast.LENGTH_LONG).show()
            return
        }

        if (confirmPassword != password) {
            Toast.makeText(this, getString(R.string.check_password), Toast.LENGTH_LONG).show()
            return
        }
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user: FirebaseUser? = mAuth.currentUser
                    //updateUI(user)

                    val userBD = user?.uid?.let { dbReference.child(it) }
                    userBD?.child("Nickname")?.setValue(userName)
                    Toast.makeText(this, getString(R.string.registered_correctly), Toast.LENGTH_LONG).show()
                    finish()

                } else {
                    if (task.exception is FirebaseAuthUserCollisionException) {
                        Toast.makeText(this, getString(R.string.user_exists), Toast.LENGTH_LONG).show()
                    }
                    if (task.exception is FirebaseAuthWeakPasswordException) {
                        Toast.makeText(this, getString(R.string.password_weak), Toast.LENGTH_LONG).show()
                    }
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    //updateUI(null)
                }
            }
    }
}
