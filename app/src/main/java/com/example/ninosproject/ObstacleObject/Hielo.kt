package com.example.ninosproject.ObstacleObject

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R

class Hielo : AbstObstaculo {
    override var newPxInit: Int = 0
    override var newPyInit: Int = 0
    override var newPxFinal: Int = 0
    override var newPyFinal: Int  = 0
    override var pxInit: Int
    override var pyInit: Int
    override var pxFinal: Int = 0
    override var pyFinal: Int = 0

    var pissed: Boolean
    var alreadyPissed: Boolean


    constructor(px: Int, py: Int){
        pxInit = px
        pyInit = py
        pxFinal = px
        pyFinal = py
        newPxInit = px
        newPyInit = py
        newPxFinal = px
        newPyFinal = py

        pissed = false
        alreadyPissed = false

    }

    override fun draw(v: GameView) {
        val p: Bitmap = BitmapFactory.decodeResource(v.resources, R.drawable.hielo_rand)
        GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
    }
}
