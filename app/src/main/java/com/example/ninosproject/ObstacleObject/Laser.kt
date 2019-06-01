package com.example.ninosproject.ObstacleObject

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R

class Laser : Trampa {
    override var pxInit: Int
    override var pyInit: Int
    override var pxFinal: Int
    override var pyFinal: Int
    override var newPxInit: Int
    override var newPyInit: Int
    override var newPxFinal: Int
    override var newPyFinal: Int
    private var aux = 0
    private var times = 0

    constructor(px: Int, py: Int){
        aux = px
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
        if(times>=0 && times<=45) {
            pxInit = aux
            newPxInit = aux
            var p = BitmapFactory.decodeResource(v.resources, R.drawable.laser_1)
            times += 1
            GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
        }
        else if(times>=46 && times<=76) {
            aux = pxInit-250.dp
            var p = BitmapFactory.decodeResource(v.resources, R.drawable.laser_2)
            times += 1
            GameThread.canvas?.drawBitmap(p, aux.toFloat(), pyInit.toFloat(), null)
        }
        else if(times>=77 && times<=92) {
            if(times == 77)
                aux = pxInit
            pxInit = aux-250.dp
            newPxInit = pxInit
            var p: Bitmap? = null
            if(times % 2 == 0){
                p = BitmapFactory.decodeResource(v.resources, R.drawable.laser_3)
            }else{
                p = BitmapFactory.decodeResource(v.resources, R.drawable.laser_4)
            }
            times += 1
            if(times == 92)
                times = 0
            GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
        }
    }
}
