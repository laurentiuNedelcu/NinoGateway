package com.example.ninosproject.Logic

import android.content.res.Resources
import android.graphics.Canvas
import com.example.ninosproject.Data.Personaje
import com.example.ninosproject.ObstacleObject.Bola
import com.example.ninosproject.ObstacleObject.Cuchilla
import com.example.ninosproject.ObstacleObject.Mur
import com.example.ninosproject.ObstacleObject.Trampa

class GameEngine{
    private lateinit var gameView: GameView
    private lateinit var canvas: Canvas
    private lateinit var murs: ArrayList<Mur>
    private var traps: ArrayList<Trampa> = ArrayList<Trampa>()
    private var change = true

    private var player: Personaje = Personaje(50,50)

    constructor(gameView: GameView, m: ArrayList<Mur>){
        var b : Bola = Bola(1000,700)
        var c : Cuchilla = Cuchilla(1000,100)
        traps.add(b)
        traps.add(c)
        this.gameView = gameView
        murs = m
    }

    fun updateL(){
        var ar = player.updateL()
        if(!colision(ar[0],ar[1])) {
            player.update(ar[0],ar[1])
        }}
    fun updateR(){
        var ar: ArrayList<Int> = player.updateR()
        if(!colision(ar[0],ar[1])) {
            player.update(ar[0],ar[1])
        }
    }
    fun updateD(){
        var ar = player.updateD()
        if(!colision(ar[0],ar[1])) {
            player.update(ar[0],ar[1])
        }
    }
    fun updateU(){
        var ar = player.updateU()
        if(!colision(ar[0],ar[1])) {
            player.update(ar[0],ar[1])
        }
    }
    fun updateInt(){
        //gameView.updatePause()
    }

    fun draw(){
        updateTrampas()
        gameView.draw(player,traps)
    }

    fun updateTrampas(){
        for(i in traps){
            var ar = i.newPosition()
            if(!colision(ar[0],ar[1])) {
                i.update(ar[0],ar[1])
            }
        }
    }

    fun colision(newPxInit: Int, newPyInit: Int):Boolean{
        var newPxFinal = newPxInit+50.dp
        var newPyFinal = newPyInit+50.dp
        for (i in murs){
            if(newPxInit>=i.pxInit && newPxInit<=i.pxFinal && newPyInit>=i.pyInit && newPyInit<=i.pyFinal){
                return true
            }else if(newPxFinal>=i.pxInit && newPxFinal<=i.pxFinal && newPyInit>=i.pyInit && newPyInit<=i.pyFinal){
                return true
            }else if(newPxFinal>=i.pxInit && newPxFinal<=i.pxFinal && newPyFinal>=i.pyInit && newPyFinal<=i.pyFinal){
                return true
            }else if(newPxInit>=i.pxInit && newPxInit<=i.pxFinal && newPyFinal>=i.pyInit && newPyFinal<=i.pyFinal){
                return true
            }
        }
        return false
    }

    val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt() // Metode per passar de density independent pixels (dp) a pixels

}
