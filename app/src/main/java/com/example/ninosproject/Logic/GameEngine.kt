package com.example.ninosproject.Logic

import android.content.res.Resources
import com.example.ninosproject.ObstacleObject.*

class GameEngine {
    private var gameView: GameView
    private var obstaculos = ArrayList<AbstObstaculo>()
    private var actionColor = false
    var direccio: Int = 0 //0->LEFT,1->DOWN,2->RIGHT,3->UP
    private var buttonUsing = false
    var arrayOfHielo: ArrayList<Hielo> = ArrayList()

    private var player: Personaje =
        Personaje(50, 50)

    constructor(gameView: GameView, m: ArrayList<AbstObstaculo>) {
        obstaculos = m
        obstaculos.add(player)
        this.gameView = gameView
        hieloInGame()
    }

    fun updateL() {
        //if (!buttonUsing) {
        player.updateL()
        if (!colision(player)) {
            player.update()
        }
        //}
    }

    fun updateR() {
        //if (!buttonUsing) {
        player.updateR()
        if (!colision(player)) {
            player.update()
        }
        //}
    }

    fun updateD() {
        //if (!buttonUsing) {
        player.updateD()
        if (!colision(player)) {
            player.update()
        }
        //}
    }

    fun updateU() {
        //if (!buttonUsing) {
        player.updateU()
        if (!colision(player)) {
            player.update()
        }
        //}
    }

    fun draw() {
        update()
        gameView.draw(obstaculos)
    }

    fun hieloInGame() {
        for (i in obstaculos) {
            if (i is Hielo)
                arrayOfHielo.add(i)
        }
    }

    fun hieloPissed(): Boolean {
        for (i in arrayOfHielo) {
            if (i.pissed)
                return true
        }
        return false
    }

    fun update() {
        var colorChange = false
        for (i in obstaculos) {
            if (i is Trampa) {
                i.newPosition()
                if (!colision(i)) {
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


    fun colision(p: AbstObstaculo): Boolean {
        var col = false
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
                        i.pissed = true
                        col = false
                    } else if (i is Personaje && p is Hielo) {
                        p.pissed = true
                        col = false
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
                        i.pissed = true
                        col = false
                    } else if (i is Personaje && p is Hielo) {
                        p.pissed = true
                        col = false
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
                        i.pissed = true
                        col = false
                    } else if (i is Personaje && p is Hielo) {
                        p.pissed = true
                        col = false
                    } else {
                        col = true
                    }
                } else if ((i.pxInit >= p.newPxInit && i.pxInit <= p.newPxFinal && i.pyFinal >= p.newPyInit && i.pyFinal <= p.newPyFinal) ||
                    (p.newPxInit >= i.pxInit && p.newPxInit <= i.pxFinal && p.newPyFinal >= i.pyInit && p.newPyFinal <= i.pyFinal)
                ) {
                    if ((i is Trampa && p is Personaje) || (i is Personaje && p is Trampa)) {
                        gameView.youLose()
                    } else if (i is Casilla && p is Personaje) {
                        i.pressed = true
                    } else if (i is Personaje && p is Casilla) {
                        p.pressed = true
                    } else if (i is Hielo && p is Personaje) {
                        i.pissed = true
                        col = false
                    } else if (i is Personaje && p is Hielo) {
                        p.pissed = true
                        col = false
                    } else {
                        col = true
                    }
                } else {
                    if (i is Hielo && p is Personaje)
                        i.pissed = false
                    else if (i is Personaje && p is Hielo)
                        p.pissed = false
                }
            }
        }
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


