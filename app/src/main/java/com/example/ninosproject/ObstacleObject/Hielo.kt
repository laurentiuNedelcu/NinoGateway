package com.example.ninosproject.ObstacleObject

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R

class Hielo : Trampa {
    override var newPxInit: Int = 0
    override var newPyInit: Int = 0
    override var newPxFinal: Int = 0
    override var newPyFinal: Int  = 0
    override var pxInit: Int
    override var pyInit: Int
    override var pxFinal: Int = 0
    override var pyFinal: Int = 0


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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun newPosition(){
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun draw(v: GameView) {
        val p: Bitmap = BitmapFactory.decodeResource(v.resources, R.drawable.hielo_rand)
        GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
    }
}
