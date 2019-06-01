package com.example.ninosproject.Data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object Firebase {

    private var dataBase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var dbReference: DatabaseReference = dataBase.reference.child("User")

    fun getReferenceUser(): DatabaseReference {
        return dbReference
    }

    fun getDatabase(): FirebaseDatabase {
        return dataBase
    }

    fun getAuth(): FirebaseAuth {
        return mAuth
    }

    fun performLogOut() {
        try {
            mAuth.signOut()
            // signed out
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}