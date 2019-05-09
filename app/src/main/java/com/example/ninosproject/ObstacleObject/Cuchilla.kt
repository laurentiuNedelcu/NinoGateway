package com.example.ninosproject.ObstacleObject

import android.graphics.BitmapFactory
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R

class Cuchilla : Trampa {
    private var pxInit: Int = 0;
    private var pyInit: Int = 0;
    private var pxFinal: Int = pxInit+50
    private var pyFinal: Int = pyInit+50
    private var times = 0

    constructor(px: Int, py: Int){
        pxInit = px
        pyInit = py
    }

    override fun update(px: Int, py: Int) {
        pxInit = px
        pyInit = py
    }

    override fun newPosition(): ArrayList<Int> {
        var ar: ArrayList<Int> = ArrayList<Int>(2)
        ar.add(pxInit)
        ar.add(pyInit)
        return ar
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
