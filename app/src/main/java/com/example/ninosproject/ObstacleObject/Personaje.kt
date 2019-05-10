package com.example.ninosproject.ObstacleObject

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.ObstacleObject.Mur
import com.example.ninosproject.R

class Personaje: AbstObstaculo{
    override var pxInit: Int = 0;
    override var pyInit: Int = 0;
    override var pxFinal: Int = pxInit+50
    override var pyFinal: Int = pyInit+50
    override var newPxInit: Int = 0;
    override var newPyInit: Int = 0;
    override var newPxFinal: Int = pxInit+50
    override var newPyFinal: Int = pyInit+50

    constructor(px: Int, py: Int){
        pxInit = px
        pyInit = py
    }

    override fun draw(v: GameView){
        var p = BitmapFactory.decodeResource(v.resources, R.drawable.circle)
        GameThread.canvas?.drawBitmap(p,pxInit.toFloat(),pyInit.toFloat(),null)
    }

    fun update(){
        pxInit = newPxInit
        pyInit = newPyInit
        pxFinal = newPyFinal
        pyFinal = newPyFinal
    }

    fun updateL(){
        newPxInit = pxInit-10
        newPxFinal = newPxInit+50.dp
        newPyInit = pyInit
        newPyFinal = newPyInit+50.dp
    }
    fun updateD(){
        newPxInit = pxInit
        newPxFinal = newPxInit+50.dp
        newPyInit = pyInit+10
        newPyFinal = newPyInit+50.dp
    }
    fun updateR(){
        newPxInit = pxInit+10
        newPxFinal = newPxInit+50.dp
        newPyInit = pyInit
        newPyFinal = newPyInit+50.dp
    }
    fun updateU(){
        newPxInit = pxInit
        newPxFinal = newPxInit+50.dp
        newPyInit = pyInit-10
        newPyFinal = newPyInit+50.dp
    }

}
