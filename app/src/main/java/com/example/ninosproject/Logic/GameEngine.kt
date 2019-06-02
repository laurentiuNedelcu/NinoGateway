package com.example.ninosproject.Logic

import android.content.res.Resources
import com.example.ninosproject.ObstacleObject.*

class GameEngine {
    private var gameView: GameView
    private var obstaculos = ArrayList<AbstObstaculo>()
    private var actionColor = false
    var direccio: Int = 0 //0->LEFT,1->DOWN,2->RIGHT,3->UP
    var hieloPissed: Boolean = false

    lateinit var player: Personaje

    constructor(gameView: GameView, m: ArrayList<AbstObstaculo>) {
        obstaculos = m
        val aux = obstaculos[obstaculos.size - 1]
        if(aux is Personaje){
            player = aux
        }
        this.gameView = gameView
    }

    fun updateL() {
        player.updateL()
        if (!colision(player, false)) {
            player.update()
        }
    }

    fun updateR() {
        player.updateR()
        if (!colision(player, false)) {
            player.update()
        }
    }

    fun updateD() {
        player.updateD()
        if (!colision(player, false)) {
            player.update()
        }
    }

    fun updateU() {
        player.updateU()
        if (!colision(player, false)) {
            player.update()
        }
    }

    fun draw() {
        update()
        gameView.draw(obstaculos)
    }

    fun update() {
        var colorChange = false
        for (i in obstaculos) {
            if (i is Trampa) {
                i.newPosition()
                if (!colision(i, hieloPissed)) {
                    i.update()
                }
            }
            if (!colorChange) {
                if (i is ActionObject) {
                    colorChange = actionRange(i)
                }
            }
        }
        gameView.changeActionButtonColor(colorChange)
    }


    fun colision(p: AbstObstaculo, hieloAux: Boolean): Boolean {
        var col = false
        var hielo =
            if (!hieloAux) false
            else hieloPissed

        for (i in obstaculos) {
            if (i != p) {
                //Punts del mur amb l'area de l'objecte
                if (p.newPxInit <= 0 || p.newPxFinal >= Resources.getSystem().displayMetrics.widthPixels || p.newPyInit <= 0 || p.newPyFinal >= Resources.getSystem().displayMetrics.heightPixels) {
                    col = true
                } else if ((i.pxInit >= p.newPxInit && i.pxInit <= p.newPxFinal && i.pyInit >= p.newPyInit && i.pyInit <= p.newPyFinal) ||
                    (p.newPxInit >= i.pxInit && p.newPxInit <= i.pxFinal && p.newPyInit >= i.pyInit && p.newPyInit <= i.pyFinal)
                ) {
                    if ((i is Trampa && p is Personaje) || (i is Personaje && p is Trampa)) {
                        gameView.youLose()
                    } else if (i is Casilla && p is Personaje) {
                        i.pressed = true
                    } else if (i is Personaje && p is Casilla) {
                        p.pressed = true
                    } else if (i is Hielo && p is Personaje) {
                        hielo = true
                    } else if (i is Personaje && p is Hielo) {
                        hielo = true
                    } else {
                        col = true
                    }
                } else if ((i.pxFinal >= p.newPxInit && i.pxFinal <= p.newPxFinal && i.pyInit >= p.newPyInit && i.pyInit <= p.newPyFinal) ||
                    (p.newPxFinal >= i.pxInit && p.newPxFinal <= i.pxFinal && p.newPyInit >= i.pyInit && p.newPyInit <= i.pyFinal)
                ) {
                    if ((i is Trampa && p is Personaje) || (i is Personaje && p is Trampa)) {
                        gameView.youLose()
                    } else if (i is Casilla && p is Personaje) {
                        i.pressed = true
                    } else if (i is Personaje && p is Casilla) {
                        p.pressed = true
                    } else if (i is Hielo && p is Personaje) {
                        hielo = true
                    } else if (i is Personaje && p is Hielo) {
                        hielo = true
                    } else {
                        col = true
                    }
                } else if ((i.pxFinal >= p.newPxInit && i.pxFinal <= p.newPxFinal && i.pyFinal >= p.newPyInit && i.pyFinal <= p.newPyFinal) ||
                    (p.newPxFinal >= i.pxInit && p.newPxFinal <= i.pxFinal && p.newPyFinal >= i.pyInit && p.newPyFinal <= i.pyFinal)
                ) {
                    if ((i is Trampa && p is Personaje) || (i is Personaje && p is Trampa)) {
                        gameView.youLose()
                    } else if (i is Casilla && p is Personaje) {
                        i.pressed = true
                    } else if (i is Personaje && p is Casilla) {
                        p.pressed = true
                    } else if (i is Hielo && p is Personaje) {
                        hielo = true
                    } else if (i is Personaje && p is Hielo) {
                        hielo = true
                    } else {
                        col = true
                    }
                } else if ((i.pxInit >= p.newPxInit && i.pxInit <= p.newPxFinal && i.pyFinal >= p.newPyInit && i.pyFinal <= p.newPyFinal) ||
                    (p.newPxInit >= i.pxInit && p.newPxInit <= i.pxFinal && p.newPyFinal >= i.pyInit && p.newPyFinal <= i.pyFinal)) {
                    if ((i is Trampa && p is Personaje) || (i is Personaje && p is Trampa)) {
                        gameView.youLose()
                    } else if (i is Casilla && p is Personaje) {
                        i.pressed = true
                    } else if (i is Personaje && p is Casilla) {
                        p.pressed = true
                    } else if (i is Hielo && p is Personaje) {
                        hielo = true
                    } else if (i is Personaje && p is Hielo) {
                        hielo = true
                    } else {
                        col = true
                    }
                }
            }
        }
        hieloPissed = hielo
        return col
    }

    fun actionRange(o: AbstObstaculo): Boolean {
        if ((o.pxInit >= player.newPxInit - 20.dp && o.pxInit <= player.newPxFinal + 20.dp && o.pyInit >= player.newPyInit - 20.dp && o.pyInit <= player.newPyFinal + 20.dp) ||
            (player.newPxInit - 20.dp >= o.pxInit && player.newPxInit - 20.dp <= o.pxFinal && player.newPyInit - 20.dp >= o.pyInit && player.newPyInit - 20.dp <= o.pyFinal)
        ) {
            if (!actionColor) {
                actionColor = true
            }
            return true
        } else if ((o.pxFinal >= player.newPxInit - 20.dp && o.pxFinal <= player.newPxFinal + 20.dp && o.pyInit >= player.newPyInit - 20.dp && o.pyInit <= player.newPyFinal + 20.dp) ||
            (player.newPxFinal + 20.dp >= o.pxInit && player.newPxFinal + 20.dp <= o.pxFinal && player.newPyInit - 20.dp >= o.pyInit && player.newPyInit - 20.dp <= o.pyFinal)
        ) {
            if (!actionColor) {
                actionColor = true
            }
            return true
        } else if ((o.pxFinal >= player.newPxInit - 20.dp && o.pxFinal <= player.newPxFinal + 20.dp && o.pyFinal >= player.newPyInit - 20.dp && o.pyFinal <= player.newPyFinal + 20.dp) ||
            (player.newPxFinal + 20.dp >= o.pxInit && player.newPxFinal + 20.dp <= o.pxFinal && player.newPyFinal + 20.dp >= o.pyInit && player.newPyFinal + 20.dp <= o.pyFinal)
        ) {
            if (!actionColor) {
                actionColor = true
            }
            return true
        } else if ((o.pxInit >= player.newPxInit - 20.dp && o.pxInit <= player.newPxFinal + 20.dp && o.pyFinal >= player.newPyInit - 20.dp && o.pyFinal <= player.newPyFinal + 20.dp) ||
            (player.newPxInit - 20.dp >= o.pxInit && player.newPxInit - 20.dp <= o.pxFinal && player.newPyFinal + 20.dp >= o.pyInit && player.newPyFinal + -20.dp <= o.pyFinal)
        ) {
            if (!actionColor) {
                actionColor = true
            }
            return true
        }
        if (actionColor) {
            actionColor = false
        }
        return false
    }

    fun actionButton() {
        for (i in obstaculos) {
            if (i is ActionObject) {
                if (actionRange(i)) {
                    i.action(gameView)


                    if (i is ResetButton) {
                        for (i in obstaculos) {
                            if (i is Casilla) {
                                i.pressed = false
                                i.alreadyPressed = false
                            }
                        }
                    }
                }
            }
        }
    }

    val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt() // Metode per passar de density independent pixels (dp) a pixels

}


