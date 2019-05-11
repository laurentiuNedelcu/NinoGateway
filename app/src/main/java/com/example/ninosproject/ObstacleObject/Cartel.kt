package com.example.ninosproject.ObstacleObject

import android.graphics.BitmapFactory
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R

class Cartel : AbstObstaculo {
    override var pxInit: Int
    override var pyInit: Int
    override var pxFinal: Int
    override var pyFinal: Int
    override var newPxInit: Int
    override var newPyInit: Int
    override var newPxFinal: Int
    override var newPyFinal: Int
    private var times: Int

    init{
        pxInit = 0
        pyInit = 0
        pxFinal = 50.dp
        pyFinal = 50.dp
        newPxInit = 0
        newPyInit = 0
        newPxFinal = 50.dp
        newPyFinal = 50.dp
        times = 0
    }

    constructor(px: Int, py: Int){
        this.pxInit = px
        this.pyInit = py
        this.pxFinal = px+50.dp
        this.pyFinal = py+50.dp
        this.newPxInit = px
        this.newPyInit = py
        this.newPxFinal = px+50.dp
        this.newPyFinal = py+50.dp
        times = 0
    }
    override fun draw(v: GameView){
        if(times>=0 && times<=2) {
            var p = BitmapFactory.decodeResource(v.resources, R.drawable.cartell_image_1)
            times += 1
            GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
        }
        else if(times>=3 && times<=5) {
            var p = BitmapFactory.decodeResource(v.resources, R.drawable.cartell_image_2)
            times += 1
            GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
        }
        else if(times>=5 && times<=7) {
            var p = BitmapFactory.decodeResource(v.resources, R.drawable.cartell_image_3)
            times += 1
            GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
        }
        else if(times>=8 && times<=10) {
            var p = BitmapFactory.decodeResource(v.resources, R.drawable.cartell_image_4)
            times += 1
            if(times == 11)
                times = 0
            GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
        }
    }
}
