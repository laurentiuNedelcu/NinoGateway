package com.example.ninosproject.ObstacleObject

import android.graphics.BitmapFactory
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R

class Personaje: AbstObstaculo{
    private var pxDraw: Int
    private var pyDraw: Int
    override var pxInit: Int = 0
    override var pyInit: Int = 0
    override var pxFinal: Int = pxInit+50
    override var pyFinal: Int = pyInit+50
    override var newPxInit: Int = 0
    override var newPyInit: Int = 0
    override var newPxFinal: Int = pxInit+50
    override var newPyFinal: Int = pyInit+50

    constructor(px: Int, py: Int){
        pxDraw = px
        pyDraw = py
        pxInit = px+8.dp //esto es para conseguir un area de 34x34 pixeles
        pyInit = py+8.dp
        pxFinal = px+34.dp
        pyFinal = py+34.dp
        newPxInit = px+8.dp
        newPyInit = py+8.dp
        newPxFinal = px+34.dp
        newPyFinal = py+34.dp
    }

    override fun draw(v: GameView){
        val p = BitmapFactory.decodeResource(v.resources, R.drawable.circle)
        GameThread.canvas?.drawBitmap(p,pxDraw.toFloat(),pyDraw.toFloat(),null)
    }

    fun update(){
        pxDraw = newPxInit-8.dp
        pyDraw = newPyInit-8.dp
        pxInit = newPxInit
        pyInit = newPyInit
        pxFinal = newPxFinal
        pyFinal = newPyFinal
    }

    fun updateL(){

        newPxInit = pxInit-10
        newPxFinal = newPxInit+34.dp
        newPyInit = pyInit
        newPyFinal = newPyInit+34.dp
    }
    fun updateD(){
        newPxInit = pxInit
        newPxFinal = newPxInit+34.dp
        newPyInit = pyInit+10
        newPyFinal = newPyInit+34.dp
    }
    fun updateR(){
        newPxInit = pxInit+10
        newPxFinal = newPxInit+34.dp
        newPyInit = pyInit
        newPyFinal = newPyInit+34.dp
    }
    fun updateU(){
        newPxInit = pxInit
        newPxFinal = newPxInit+34.dp
        newPyInit = pyInit-10
        newPyFinal = newPyInit+34.dp
    }

    fun move(): Boolean {
        return when {
            pxFinal == newPxFinal -> true
            pxInit == newPxInit -> true
            pyFinal == newPyFinal -> true
            pyInit == newPyFinal -> true
            else -> false
        }
    }

}
