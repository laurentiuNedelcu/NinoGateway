package com.example.ninosproject.Data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

object LevelsArrays {
    private var levelsUnlocked: ArrayList<String> = ArrayList()

    fun getLevelsUnlocked(): ArrayList<String> {
        return levelsUnlocked
    }

    fun initList() {
        if (Guest.getGuest()) {
            var i = 0
            levelsUnlocked.add("1")
            while (i < 10) {
                levelsUnlocked.add("0")
                i++
            }
            levelsUnlocked[4] = "1"
        } else {
            levelsFirebase(Firebase.getAuth().uid.toString())
        }
    }

    fun resetList() {
        levelsUnlocked.clear()
    }

    fun setLevel(pos: Int, value: String) {
        levelsUnlocked[pos] = value
    }

    private fun levelsFirebase(key: String) {
        Firebase.getReferenceUser().child(key).child("Levels")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (level in dataSnapshot.children) {
                        levelsUnlocked.add(level.value.toString())
                    }
                }
            }
            )
    }
}