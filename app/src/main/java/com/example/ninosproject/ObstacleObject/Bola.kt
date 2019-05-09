package com.example.ninosproject.ObstacleObject

import android.graphics.BitmapFactory
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R
import java.util.*
import kotlin.collections.ArrayList

class Bola : Trampa{
    private var pxInit: Int = 0;
    private var pyInit: Int = 0;
    private var times: Int = 0;
    private var sumX: Int = 0;
    private var sumY:Int  = 0;

    constructor(px: Int, py: Int){
        pxInit = px
        pyInit = py
    }

    override fun draw(v: GameView){
        var p = BitmapFactory.decodeResource(v.resources, R.drawable.bola_pinchos)
        GameThread.canvas?.drawBitmap(p,pxInit.toFloat(),pyInit.toFloat(),null)
    }

    override fun update(px: Int, py: Int){
        pxInit = px
        pyInit = py
    }

    override  fun newPosition():ArrayList<Int>{
        var i = Math.random()
        var ar: ArrayList<Int> = ArrayList<Int>(2)
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
        ar.add(pxInit+sumX)
        ar.add(pyInit+sumY)
        return ar
    }
}
