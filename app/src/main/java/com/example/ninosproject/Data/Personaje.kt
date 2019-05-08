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

    fun updateL(m:ArrayList<Mur>){
        var newPxInit = pxInit-10
        if(!colision(newPxInit,pyInit,m)) {
            this.pxInit -= 10
        }
    }
    fun updateD(m:ArrayList<Mur>){
        var newPyInit = pyInit+10
        if(!colision(pxInit,newPyInit,m)) {
            this.pyInit += 10
        }
    }
    fun updateR(m:ArrayList<Mur>){
        var newPxInit = pxInit+10
        if(!colision(newPxInit,pyInit,m)) {
            this.pxInit += 10
        }
    }
    fun updateU(m:ArrayList<Mur>){
        var newPyInit = pyInit-10
        if(!colision(pxInit,newPyInit,m)) {
            this.pyInit -= 10
        }
    }

    fun colision(newPxInit: Int, newPyInit: Int, m: ArrayList<Mur>):Boolean{
        var newPxFinal = newPxInit+50.dp
        var newPyFinal = newPyInit+50.dp
        for (i in m){
            if(newPxInit>=i.pxInit && newPxInit<=i.pxFinal && newPyInit>=i.pyInit && newPyInit<=i.pyFinal){
                return true
            }else if(newPxFinal>=i.pxInit && newPxFinal<=i.pxFinal && newPyInit>=i.pyInit && newPyInit<=i.pyFinal){
                return true
            }else if(newPxFinal>=i.pxInit && newPxFinal<=i.pxFinal && newPyFinal>=i.pyInit && newPyFinal<=i.pyFinal){
                return true
            }else if(newPxInit>=i.pxInit && newPxInit<=i.pxFinal && newPyFinal>=i.pyInit && newPyFinal<=i.pyFinal){
                return true
            }
        }
        return false
    }

    val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt() // Metode per passar de density independent pixels (dp) a pixels

}
