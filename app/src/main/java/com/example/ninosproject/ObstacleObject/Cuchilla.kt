package com.example.ninosproject.ObstacleObject

import android.content.res.Resources
import android.graphics.BitmapFactory
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R

class Cuchilla : Trampa {
    override var pxInit: Int
    override var pyInit: Int
    override var pxFinal: Int
    override var pyFinal: Int
    override var newPxInit: Int
    override var newPyInit: Int
    override var newPxFinal: Int
    override var newPyFinal: Int
    private var times = 0

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
    }

    override fun newPosition(){
    }

    override fun draw(v: GameView){
        if(times>=0 && times<=2) {
            var p = BitmapFactory.decodeResource(v.resources, R.drawable.cuchillas_image_1)
            times += 1
            GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
        }
        else if(times>=3 && times<=5) {
            var p = BitmapFactory.decodeResource(v.resources, R.drawable.cuchillas_image_2)
            times += 1
            GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
        }
        else if(times>=5 && times<=7) {
            var p = BitmapFactory.decodeResource(v.resources, R.drawable.cuchillas_image_3)
            times += 1
            if(times == 8)
                times = 0
            GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
        }
    }
}
