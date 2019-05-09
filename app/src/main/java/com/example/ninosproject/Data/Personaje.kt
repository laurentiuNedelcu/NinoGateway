package com.example.ninosproject.Data

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.ObstacleObject.Mur
import com.example.ninosproject.R

class Personaje{
    private var pxInit: Int = 0;
    private var pyInit: Int = 0;
    private var pxFinal: Int = pxInit+50
    private var pyFinal: Int = pyInit+50

    constructor(px: Int, py: Int){
        pxInit = px
        pyInit = py
    }

    fun draw(v: GameView){
        var p = BitmapFactory.decodeResource(v.resources, R.drawable.circle)
        GameThread.canvas?.drawBitmap(p,pxInit.toFloat(),pyInit.toFloat(),null)
    }

    fun update(px: Int, py: Int){
        pxInit = px
        pyInit = py
    }

    fun updateL():ArrayList<Int>{
        var ar: ArrayList<Int> = ArrayList<Int>(2)
        ar.add(pxInit-10)
        ar.add(pyInit)
        return ar
    }
    fun updateD():ArrayList<Int>{
        var ar: ArrayList<Int> = ArrayList<Int>(2)
        ar.add(pxInit)
        ar.add(pyInit+10)
        return ar
    }
    fun updateR():ArrayList<Int>{
        var ar: ArrayList<Int> = ArrayList<Int>(2)
        ar.add(pxInit+10)
        ar.add(pyInit)
        return ar
    }
    fun updateU():ArrayList<Int>{
        var ar: ArrayList<Int> = ArrayList<Int>(2)
        ar.add(pxInit)
        ar.add(pyInit-10)
        return ar
    }
}
