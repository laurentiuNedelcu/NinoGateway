package com.example.ninosproject.ObstacleObject

import android.graphics.BitmapFactory
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R

class Casilla : AbstObstaculo {
    override var newPxInit: Int = 0
    override var newPyInit: Int = 0
    override var newPxFinal: Int = 0
    override var newPyFinal: Int  = 0
    override var pxInit: Int
    override var pyInit: Int
    override var pxFinal: Int = 0
    override var pyFinal: Int = 0
    var valor : Int

    constructor(px: Int, py: Int, valor : Int){
        pxInit = px
        pyInit = py
        this.valor = valor
    }


    override fun draw(v: GameView) {
        var p = BitmapFactory.decodeResource(v.resources, R.drawable.casilla_random)
        GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
    }
}
