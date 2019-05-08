package com.example.ninosproject.Logic

import android.graphics.Canvas
import com.example.ninosproject.Data.Personaje
import com.example.ninosproject.ObstacleObject.Mur

class GameEngine{
    private lateinit var gameView: GameView
    private lateinit var canvas: Canvas
    private lateinit var murs: ArrayList<Mur>
    private var change = true

    private var player: Personaje = Personaje(50,50)

    constructor(gameView: GameView, m: ArrayList<Mur>){
        this.gameView = gameView
        murs = m
    }

    fun updateL(){
        player.updateL(murs)
    }
    fun updateR(){
        player.updateR(murs)
    }
    fun updateD(){
        player.updateD(murs)
    }
    fun updateU(){
        player.updateU(murs)
    }

    fun draw(){
        gameView.draw(player)
    }
}
