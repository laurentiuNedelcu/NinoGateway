package com.example.ninosproject.ObstacleObject

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R

class Mur: AbstObstaculo{
    private lateinit var rect: Bitmap
    override var pxInit: Int
    override var pyInit: Int
    override var pxFinal: Int
    override var pyFinal: Int
    override var newPxInit: Int
    override var newPyInit: Int
    override var newPxFinal: Int
    override var newPyFinal: Int
    private var type: Int

    init{
        pxInit = 0
        pyInit = 0
        pxFinal = 25.dp
        pyFinal = 25.dp
        newPxInit = 0
        newPyInit = 0
        newPxFinal = 25.dp
        newPyFinal = 25.dp
    }

    constructor(px: Int, py: Int, type: Int){
        this.pxInit = px
        this.pyInit = py
        this.pxFinal = px+25.dp
        this.pyFinal = py+25.dp
        this.newPxInit = px
        this.newPyInit = py
        this.newPxFinal = px+25.dp
        this.newPyFinal = py+25.dp
        this.type = type

        if(type == 10){
            pxFinal = px+50.dp
            pyFinal = py+50.dp
            newPxFinal = px+50.dp
            newPyFinal = py+50.dp
        }else if(type == 20){
            pyFinal = py+50.dp
            newPyFinal = py+50.dp
        }
    }

    override fun draw(v: GameView){

        when(type){
            1 -> rect = BitmapFactory.decodeResource(v.resources, R.drawable.mur)
            2 -> rect = BitmapFactory.decodeResource(v.resources, R.drawable.mur_hor)
            3 -> rect = BitmapFactory.decodeResource(v.resources, R.drawable.mur_down_to_left)
            4 -> rect = BitmapFactory.decodeResource(v.resources, R.drawable.mur_down_to_right)
            5 -> rect = BitmapFactory.decodeResource(v.resources, R.drawable.mur_up_to_left)
            6 -> rect = BitmapFactory.decodeResource(v.resources, R.drawable.mur_up_to_right)
            7 -> rect = BitmapFactory.decodeResource(v.resources, R.drawable.mur_hor_tancat_d)
            8 -> rect = BitmapFactory.decodeResource(v.resources, R.drawable.mur_hor_tancat_i)

            10 -> rect = BitmapFactory.decodeResource(v.resources, R.drawable.caixa)

            20 -> rect = BitmapFactory.decodeResource(v.resources, R.drawable.sofa)
        }

        GameThread.canvas?.drawBitmap(rect,pxInit.toFloat(),pyInit.toFloat(),null)
    }
}