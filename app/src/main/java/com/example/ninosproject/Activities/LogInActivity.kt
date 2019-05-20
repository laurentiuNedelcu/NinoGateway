package com.example.ninosproject.Activities

import android.content.Intent
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
import com.google.firebase.database.*


class LogInActivity : AppCompatActivity() {

    private lateinit var logInButton: Button
    private lateinit var registerButton: Button
    private lateinit var guestButton: Button
    private lateinit var emailText: TextView
    private lateinit var passwordText: TextView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var firebaseRef: DatabaseReference
    private lateinit var dataBase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_log_in)

        mAuth = FirebaseAuth.getInstance()
        dataBase = FirebaseDatabase.getInstance()
        firebaseRef = dataBase.reference.child("User")

        logInButton = findViewById(R.id.logIn_id)
        registerButton = findViewById(R.id.register_id)
        guestButton = findViewById(R.id.guest_id)
        emailText = findViewById(R.id.emailText)
        passwordText = findViewById(R.id.passwordText)

        logInButton.setOnClickListener { logIn() }
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        guestButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun logIn() {
        val username: String = emailText.text.toString().trim()
        val password: String = passwordText.text.toString().trim()

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, getString(R.string.Ingresar_email), Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, getString(R.string.Ingresar_password), Toast.LENGTH_LONG).show()
            return
        }

        //Comprovem si l'usuari ha introduit un correu
        if ((android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches())) {
            performLogin(username, password)
        } else {
            //Sino busquem el correu electronic associat al nom d'usuari
            firebaseRef.addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (id in dataSnapshot.children) {
                                val aux = id.key.toString()
                                firebaseRef.child(aux).child("Nickname").child(username)
                                    .addListenerForSingleValueEvent(
                                        object : ValueEventListener {
                                            override fun onCancelled(p0: DatabaseError) {
                                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                            }

                                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                                if (dataSnapshot.exists()) {
                                                    val userId = dataSnapshot.getValue(String::class.java)
                                                    performLogin(userId, password)
                                                }
                                            }
                                        })
                            }
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Failed to read value
                    }
                }
            )
        }
    }


    fun performLogin(username: String?, password: String) {
        if (username != null) {
            mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            this, getString(R.string.incorrect_user_password),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
            Toast.makeText(this, "Username is null", Toast.LENGTH_SHORT).show()
        }
    }

    public override fun onStart() {
        super.onStart()
    }
}