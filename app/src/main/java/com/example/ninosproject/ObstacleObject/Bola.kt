package com.example.ninosproject.ObstacleObject

import android.content.res.Resources
import android.graphics.BitmapFactory
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R
import java.util.*
import kotlin.collections.ArrayList

class Bola : Trampa{
    private var pxDraw:Int
    private var pyDraw:Int
    override var pxInit: Int
    override var pyInit: Int
    override var pxFinal: Int
    override var pyFinal: Int
    override var newPxInit: Int
    override var newPyInit: Int
    override var newPxFinal: Int
    override var newPyFinal: Int
    private var times: Int = 0;
    private var sumX: Int = 0;
    private var sumY:Int  = 0;

    constructor(px: Int, py: Int){
        pxDraw = px
        pyDraw = py
        pxInit = px+8.dp
        pyInit = py+8.dp
        pxFinal = px+34.dp
        pyFinal = py+34.dp
        newPxInit = px+8.dp
        newPyInit = py+8.dp
        newPxFinal = px+34.dp
        newPyFinal = py+34.dp
    }

    override fun update() {
        pxDraw = newPxInit-8.dp
        pyDraw = newPyInit-8.dp
        pxInit = newPxInit
        pyInit = newPyInit
        pxFinal = newPxFinal
        pyFinal = newPyFinal
    }

    override fun draw(v: GameView){
        var p = BitmapFactory.decodeResource(v.resources, R.drawable.bola_pinchos)
        GameThread.canvas?.drawBitmap(p,pxDraw.toFloat(),pyDraw.toFloat(),null)
    }

    override  fun newPosition(){
        var i = Math.random()
        if(times == 0) {
            if (i < 0.25) {
                sumX = -5
                sumY = 0
            } else if (i < 0.50) {
                sumX = +5
                sumY = 0
            } else if (i < 0.75) {
                sumX = 0
                sumY = -5
            } else if (i < 1) {
                sumX = 0
                sumY = +5
            }
            times+=1
        }else if(times<15){
            times += 1

        }else{
            times = 0
        }
        newPxInit = (pxInit+sumX)
        newPyInit = (pyInit+sumY)
        newPxFinal = (pxInit+sumX)+34.dp
        newPyFinal = (pyInit+sumY)+34.dp
    }
}
