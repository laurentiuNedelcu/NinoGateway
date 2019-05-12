package com.example.ninosproject.ObstacleObject

import android.graphics.BitmapFactory
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R

class Puerta : AbstObstaculo {
    override var pxInit: Int
    override var pyInit: Int
    override var pxFinal: Int
    override var pyFinal: Int
    override var newPxInit: Int
    override var newPyInit: Int
    override var newPxFinal: Int
    override var newPyFinal: Int
    private var open = false

    init{
        pxInit = 0
        pyInit = 0
        pxFinal = 25.dp
        pyFinal = 50.dp
        newPxInit = 0
        newPyInit = 0
        newPxFinal = 25.dp
        newPyFinal = 50.dp
    }

    constructor(px: Int, py: Int){
        this.pxInit = px
        this.pyInit = py
        this.pxFinal = px+25.dp
        this.pyFinal = py+50.dp
        this.newPxInit = px
        this.newPyInit = py
        this.newPxFinal = px+25.dp
        this.newPyFinal = py+50.dp
    }

    override fun draw(v: GameView) {
        if(open){
            var p = BitmapFactory.decodeResource(v.resources, R.drawable.puerta1)
            GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
        }else{
            var p = BitmapFactory.decodeResource(v.resources, R.drawable.puerta2)
            GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
        }
    }

    fun changeLock(){
        open = !open
    }
}
