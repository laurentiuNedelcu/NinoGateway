package com.example.ninosproject.ObstacleObject

import android.content.res.Resources
import android.graphics.BitmapFactory
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R
import java.util.*
import kotlin.collections.ArrayList

class Bola : Trampa{
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
        pxInit = px
        pyInit = py
        pxFinal = px+50.dp
        pyFinal = py+50.dp
        newPxInit = px
        newPyInit = py
        newPxFinal = px+50.dp
        newPyFinal = py+50.dp
    }

    override fun update() {
        pxInit = newPxInit
        pyInit = newPyInit
        pxFinal = newPxFinal
        pyFinal = newPyFinal
    }

    override fun draw(v: GameView){
        var p = BitmapFactory.decodeResource(v.resources, R.drawable.bola_pinchos)
        GameThread.canvas?.drawBitmap(p,pxInit.toFloat(),pyInit.toFloat(),null)
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
        newPxFinal = (pxInit+sumX)+50.dp
        newPyFinal = (pyInit+sumY)+50.dp
    }
}
