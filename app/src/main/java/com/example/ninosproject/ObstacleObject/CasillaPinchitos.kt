package com.example.ninosproject.ObstacleObject

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R

class CasillaPinchitos : Trampa {
    override var pxInit: Int
    override var pyInit: Int
    override var pxFinal: Int
    override var pyFinal: Int
    override var newPxInit: Int
    override var newPyInit: Int
    override var newPxFinal: Int
    override var newPyFinal: Int
    private var times = 0
    private var pxDraw = 0
    private var pyDraw = 0

    constructor(px: Int, py: Int){
        pxDraw = px
        pyDraw = py
        pxInit = -100.dp
        pyInit = -100.dp
        pxFinal = -100.dp
        pyFinal = -100.dp
        newPxInit = -100.dp
        newPyInit = -100.dp
        newPxFinal = -100.dp
        newPyFinal = -100.dp
    }

    override fun update() {
    }

    override fun newPosition(){
    }

    override fun draw(v: GameView){
        if(times>=0 && times<=45) {
            var p = BitmapFactory.decodeResource(v.resources, R.drawable.pinchos_1)
            times += 1
            GameThread.canvas?.drawBitmap(p, pxDraw.toFloat(), pyDraw.toFloat(), null)
        }
        else if(times>=46 && times<=76) {
            var p = BitmapFactory.decodeResource(v.resources, R.drawable.pinchos_2)
            times += 1
            GameThread.canvas?.drawBitmap(p, pxDraw.toFloat(), pyDraw.toFloat(), null)
        }
        else if(times>=77 && times<=107) {
            pxInit = pxDraw
            pyInit = pyDraw
            pxFinal = pxInit+50.dp
            pyFinal = pyInit+50.dp
            newPxInit = pxDraw
            newPyInit = pyDraw
            newPxFinal = pxInit+50.dp
            newPyFinal = pyInit+50.dp
            var p: Bitmap? = null
            if(times<=80) {
                p = BitmapFactory.decodeResource(v.resources, R.drawable.pinchos_3)
            }else{
                p = BitmapFactory.decodeResource(v.resources, R.drawable.pinchos_4)
            }
            times += 1
            if(times == 107) {
                times = 0
                pxInit = -100.dp
                pyInit = -100.dp
                pxFinal = -100.dp
                pyFinal = -100.dp
                newPxInit = -100.dp
                newPyInit = -100.dp
                newPxFinal = -100.dp
                newPyFinal = -100.dp
            }
            GameThread.canvas?.drawBitmap(p, pxDraw.toFloat(), pyDraw.toFloat(), null)
        }
    }
}
