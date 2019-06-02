package com.example.ninosproject.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.ninosproject.Data.Firebase
import com.example.ninosproject.Data.Guest
import com.example.ninosproject.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class LogInActivity : AppCompatActivity() {

    private lateinit var sfx: String
    private lateinit var musica: String

    private lateinit var logInButton: Button
    private lateinit var registerButton: Button
    private lateinit var guestButton: Button
    private lateinit var emailText: TextView
    private lateinit var passwordText: TextView
    private lateinit var progressBar: ProgressBar
    private var userList: HashMap<String, String> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_log_in)

        logInButton = findViewById(R.id.logIn_id)
        registerButton = findViewById(R.id.register_id)
        guestButton = findViewById(R.id.guest_id)
        emailText = findViewById(R.id.emailText)
        passwordText = findViewById(R.id.passwordText)
        progressBar = findViewById(R.id.progressBar)

        progressBar.visibility = View.GONE

        logInButton.setOnClickListener { logIn() }
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        guestButton.setOnClickListener {
            Guest.setGuest(true)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        Firebase.getReferenceUser().addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (id in dataSnapshot.children) {
                            for (dades in id.children) {
                                if (dades.key.toString().equals("Nickname")) {
                                    val aux = dades.value.toString() //retorna un string tal que: {nickname=email}
                                    val nickname = aux.subSequence(1, aux.indexOf('='))
                                    val mail = aux.subSequence(aux.indexOf('=') + 1, aux.length - 1)
                                    if (!userList.containsKey(nickname)) {
                                        userList[nickname.toString()] = mail.toString()
                                    }
                                }
                            }
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Failed to read value
                }
            }
        )
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

        progressBar.visibility = View.VISIBLE

        //Comprovem si l'usuari ha introduit un correu
        if ((android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches())) {
            if (userList.containsValue(username)) {
                performLogin(username, password)
            }
        } else {
            if (userList.containsKey(username)) {
                performLogin(userList[username], password)
            } else {
                userIncorrect()
                progressBar.visibility = View.GONE
            }
        }
    }


    fun performLogin(username: String?, password: String) {
        if (username != null) {
            Firebase.getAuth().signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Guest.setGuest(false)
                        search(
                            Firebase.getAuth().currentUser?.uid.toString(),
                            1,
                            "sfx"
                        ) //Action 1 llegir, action 2 = escriure
                        search(Firebase.getAuth().currentUser?.uid.toString(), 1, "music")
                        // Sign in success, update UI with the signed-in user's information
                        val intent = Intent(this, MainActivity::class.java)
                        finish()
                        startActivity(intent)
                        progressBar.visibility = View.GONE
                    } else {
                        userIncorrect()
                        progressBar.visibility = View.GONE
                    }
                }
        }
    }

    private fun userIncorrect() {
        Toast.makeText(this, getString(R.string.incorrect_user_password), Toast.LENGTH_SHORT).show()
    }

    private fun search(key: String, action: Int, audio: String) {
        Firebase.getReferenceUser().addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (action == 1) {
                    Firebase.getReferenceUser().child(key).child("Audio").child(audio).addListenerForSingleValueEvent(
                        object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                if (audio == "sfx")
                                    sfx = dataSnapshot.value.toString()
                                else
                                    musica = dataSnapshot.value.toString()
                            }
                        }
                    )
                } else if (action == 2) {
                    if (audio == "sfx")
                        Firebase.getReferenceUser().child(key).child("Audio").child(audio).setValue(sfx)
                    else
                        Firebase.getReferenceUser().child(key).child("Audio").child(audio).setValue(musica)
                }
            }
        })
    }

    public override fun onStart() {
        super.onStart()
    }
}