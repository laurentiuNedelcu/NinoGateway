package com.example.ninosproject.Data

object Guest {
    private var guest: Boolean = true
    private var levelsUnlocked: ArrayList<String> = ArrayList()

    fun getLevelsUnlocked(): ArrayList<String> {
        return levelsUnlocked
    }

    fun setGuest(guest: Boolean) {
        this.guest = guest
    }

    fun getGuest(): Boolean {
        return guest
    }
}