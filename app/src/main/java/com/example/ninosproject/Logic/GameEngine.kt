package com.example.ninosproject.Logic

import android.content.res.Resources
import android.graphics.Color
import com.example.ninosproject.ObstacleObject.*

class GameEngine{
    private lateinit var gameView: GameView
    private var obstaculos = ArrayList<AbstObstaculo>()
    private var actionColor = false

    private var player: Personaje =
        Personaje(50, 50)
    private var valorActual : Int = 0 //variable donde se va sumando los valores de las casillas por las que pasa

    constructor(gameView: GameView, m: ArrayList<AbstObstaculo>){
        var b : Bola = Bola(1050,700)
        var c : Cuchilla = Cuchilla(1000,100)
        obstaculos = m
        var x = 1000
        var y = 300

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
        update()
        gameView.draw(obstaculos)
    }

    fun update() {
        for (i in obstaculos) {
            if (i is Trampa) {
                var ar = i.newPosition()
                if (!colision(i)) {
                    i.update()
                }
            }
            else if(i is Cartel){
                actionRange(i)
            }
        }
    }


    fun colision(p: AbstObstaculo):Boolean{
        for (i in obstaculos){
            if (!i.equals(p)) {
                //Punts del mur amb l'area de l'objecte
                if (p.newPxInit <= 0 || p.newPxFinal >= Resources.getSystem().displayMetrics.widthPixels || p.newPyInit <= 0 || p.newPyFinal >= Resources.getSystem().displayMetrics.heightPixels) {
                    if(i is Trampa && p is Personaje){
                            gameView.youLose()
                        }
                    else if(i is Casilla && p is Personaje){
                        i.pressed = true
                        return false
                    }
                    return true
                } else if (i.pxInit >= p.newPxInit && i.pxInit <= p.newPxFinal && i.pyInit >= p.newPyInit && i.pyInit <= p.newPyFinal) {
                    if(i is Trampa && p is Personaje){
                        gameView.youLose()
                    }
                    else if(i is Casilla && p is Personaje){
                        i.pressed = true
                        return false
                    }
                    return true
                } else if (i.pxFinal >= p.newPxInit && i.pxFinal <= p.newPxFinal && i.pyInit >= p.newPyInit && i.pyInit <= p.newPyFinal) {
                    if(i is Trampa && p is Personaje){
                        gameView.youLose()
                    }
                    else if(i is Casilla && p is Personaje){
                        i.pressed = true
                        return false
                    }
                    return true
                } else if (i.pxFinal >= p.newPxInit && i.pxFinal <= p.newPxFinal && i.pyFinal >= p.newPyInit && i.pyFinal <= p.newPyFinal) {
                    if(i is Trampa && p is Personaje){
                        gameView.youLose()
                    }
                    else if(i is Casilla && p is Personaje){
                        i.pressed = true
                        return false
                    }
                    return true
                } else if (i.pxInit >= p.newPxInit && i.pxInit <= p.newPxFinal && i.pyFinal >= p.newPyInit && i.pyFinal <= p.newPyFinal) {
                    if(i is Trampa && p is Personaje){
                        gameView.youLose()
                    }else if(i is Casilla && p is Personaje){
                        i.pressed = true
                        return false
                    }
                    return true
                }
            }
        }
        return false
    }

    fun actionRange(o: AbstObstaculo): Boolean{
            if (o.pxInit >= player.newPxInit-20.dp  && o.pxInit <= player.newPxFinal+20.dp   && o.pyInit >= player.newPyInit-20.dp   && o.pyInit <= player.newPyFinal+20.dp  ) {
                if(!actionColor) {
                    actionColor = true
                    gameView.changeActionButtonColor(actionColor)
                }
                return true
            } else if (o.pxFinal >= player.newPxInit-20.dp && o.pxFinal <= player.newPxFinal+20.dp && o.pyInit >= player.newPyInit-20.dp && o.pyInit <= player.newPyFinal+20.dp) {
                if(!actionColor) {
                    actionColor = true
                    gameView.changeActionButtonColor(actionColor)
                }
                return true
            } else if (o.pxFinal >= player.newPxInit-20.dp && o.pxFinal <= player.newPxFinal+20.dp  && o.pyFinal >= player.newPyInit-20.dp  && o.pyFinal <= player.newPyFinal+20.dp ) {
                if(!actionColor) {
                    actionColor = true
                    gameView.changeActionButtonColor(actionColor)
                }
                return true
            } else if (o.pxInit >= player.newPxInit-20.dp && o.pxInit <= player.newPxFinal+20.dp && o.pyFinal >= player.newPyInit-20.dp  && o.pyFinal <= player.newPyFinal+20.dp ) {
                if(!actionColor) {
                    actionColor = true
                    gameView.changeActionButtonColor(actionColor)
                }
                return true
            }
        if(actionColor){
            actionColor = false
            gameView.changeActionButtonColor(actionColor)
        }
        return false
    }

    fun actionButton(){
        for (i in obstaculos){
            if(i is Cartel){
                if(actionRange(i)) {
                    gameView.youLose()
                }
            }
        }
    }

    val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt() // Metode per passar de density independent pixels (dp) a pixels

}
