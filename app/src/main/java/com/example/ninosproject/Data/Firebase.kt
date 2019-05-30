package com.example.ninosproject.Data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object Firebase {

    private var dataBase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var dbReference: DatabaseReference = dataBase.reference.child("User")
    private var sfx: String = ""
    private var guest: Boolean = true

    fun getReferenceUser(): DatabaseReference {
        return dbReference
    }

    fun setSFXValue(sfx: String) {
        this.sfx = sfx
    }

    fun getSFX(): String {
        return sfx
    }

    fun setGuest(guest: Boolean) {
        this.guest = guest
    }

    fun getGuest(): Boolean {
        return guest
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