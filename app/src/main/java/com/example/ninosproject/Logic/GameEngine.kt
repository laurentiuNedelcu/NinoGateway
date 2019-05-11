package com.example.ninosproject.Logic

import android.content.res.Resources
import com.example.ninosproject.ObstacleObject.*

class GameEngine{
    private lateinit var gameView: GameView
    private var obstaculos = ArrayList<AbstObstaculo>()

    private var player: Personaje =
        Personaje(50, 50)
    private var valorActual : Int = 0 //variable donde se va sumando los valores de las casillas por las que pasa

    constructor(gameView: GameView, m: ArrayList<AbstObstaculo>){
        var b : Bola = Bola(1050,700)
        var c : Cuchilla = Cuchilla(1000,100)
        obstaculos = m
        var x = 1000
        var y = 300
        for (i in 1..5) {
            var casilla: Casilla = Casilla(x, y, i)
            obstaculos.add(casilla)
            x =(130..900).random()
            y = (270..900).random()
        }

        obstaculos.add(b)
        obstaculos.add(c)
        obstaculos.add(player)
        this.gameView = gameView
    }

    fun updateL(){
        player.updateL()
        if(!colision(player)) {
            player.update()
        }
    }
    fun updateR(){
        player.updateR()
        if(!colision(player)) {
            player.update()
        }
    }
    fun updateD(){
        player.updateD()
        if(!colision(player)) {
            player.update()
        }
    }
    fun updateU(){
        player.updateU()
        if(!colision(player)) {
            player.update()
        }
    }

    fun draw(){
        updateTrampas()
        gameView.draw(obstaculos)
    }

    fun updateTrampas() {
        for (i in obstaculos) {
            if (i is Trampa) {
                var ar = i.newPosition()
                if (!colision(i)) {
                    i.update()
                }
            }
        }
    }


    fun colision(p: AbstObstaculo):Boolean{
        for (i in obstaculos){
            if (!i.equals(p)) {
                //Punts del mur amb l'area de l'objecte
                if (p.newPxInit <= 0 || p.newPxFinal >= Resources.getSystem().displayMetrics.widthPixels || p.newPyInit <= 0 || p.newPyFinal >= Resources.getSystem().displayMetrics.heightPixels) {
                    if((p is Trampa && i is Personaje) || (i is Trampa && p is Personaje)){
                            gameView.youLose()
                        }
                    return true
                } else if (i.pxInit >= p.newPxInit && i.pxInit <= p.newPxFinal && i.pyInit >= p.newPyInit && i.pyInit <= p.newPyFinal) {
                    if((p is Trampa && i is Personaje) || (i is Trampa && p is Personaje)){
                        gameView.youLose()
                    }
                    return true
                } else if (i.pxFinal >= p.newPxInit && i.pxFinal <= p.newPxFinal && i.pyInit >= p.newPyInit && i.pyInit <= p.newPyFinal) {
                    if((p is Trampa && i is Personaje) || (i is Trampa && p is Personaje)){
                        gameView.youLose()
                    }
                    return true
                } else if (i.pxFinal >= p.newPxInit && i.pxFinal <= p.newPxFinal && i.pyFinal >= p.newPyInit && i.pyFinal <= p.newPyFinal) {
                    if((p is Trampa && i is Personaje) || (i is Trampa && p is Personaje)){
                        gameView.youLose()
                    }
                    return true
                } else if (i.pxInit >= p.newPxInit && i.pxInit <= p.newPxFinal && i.pyFinal >= p.newPyInit && i.pyFinal <= p.newPyFinal) {
                    if((p is Trampa && i is Personaje) || (i is Trampa && p is Personaje)){
                        gameView.youLose()
                    }
                    return true
                }
            }else if(i.equals(p)){
                //colisionar
            }
        }
        return false
    }
}
