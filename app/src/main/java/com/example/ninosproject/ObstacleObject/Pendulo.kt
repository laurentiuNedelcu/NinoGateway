package com.example.ninosproject.ObstacleObject

import android.graphics.BitmapFactory
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R

class Pendulo : Trampa{
    private var pxDraw:Int
    private var pyDraw:Int
    override var pxInit: Int
    override var pyInit: Int
    override var pxFinal: Int
    override var pyFinal: Int
    override var newPxInit: Int
    override var newPyInit: Int
    override var newPxFinal: Int
    override var newPyFinal: Int
    private var times: Int = 0;
    private var move_up: Boolean = true

    constructor(px: Int, py: Int){
        pxDraw = px
        pyDraw = py
        this.pxInit = px
        this.pyInit = py
        this.pxFinal = px+25.dp
        this.pyFinal = py+50.dp
        this.newPxInit = px
        this.newPyInit = py
        this.newPxFinal = px+25.dp
        this.newPyFinal = py+50.dp
    }

    override fun update() {
        pxDraw = newPxInit
        pyDraw = newPyInit
        pxInit = newPxInit
        pyInit = newPyInit
        pxFinal = newPxFinal
        pyFinal = newPyFinal
    }

    override fun draw(v: GameView){
        var p = BitmapFactory.decodeResource(v.resources, R.drawable.pendulo_1)
        if(times == 1){
            if(move_up){
                p = BitmapFactory.decodeResource(v.resources,R.drawable.pendulo_up_2)
            }else{
                p = BitmapFactory.decodeResource(v.resources,R.drawable.pendulo_down_2)
            }
        }else if(times == 2){
            if(move_up){
                p = BitmapFactory.decodeResource(v.resources,R.drawable.pendulo_up_3)
            }else{
                p = BitmapFactory.decodeResource(v.resources,R.drawable.pendulo_down_3)
            }
        }
        GameThread.canvas?.drawBitmap(p,pxDraw.toFloat(),pyDraw.toFloat(),null)
    }

    override  fun newPosition(){
        if(move()){
            if(move_up){
                newPyInit = (pyInit-10)
                newPyFinal = (pyInit-10)+50.dp
            }else{
                newPyInit = (pyInit+10)
                newPyFinal = (pyInit+10)+50.dp
            }
        }
    }
    private fun move():Boolean{
        if(pyInit==newPyInit){
            times = 0
            return true
        }else{
            if(times <= 30){
                if(times == 30){
                    times = 0
                    move_up = !move_up
                    return true
                }
                times++
            }
            return false
        }
    }
}
