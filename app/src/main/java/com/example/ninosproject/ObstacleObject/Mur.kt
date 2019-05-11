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
        this.type = type
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

            10 -> rect = BitmapFactory.decodeResource(v.resources, R.drawable.caixa1)
            11 -> rect = BitmapFactory.decodeResource(v.resources, R.drawable.caixa2)
            12 -> rect = BitmapFactory.decodeResource(v.resources, R.drawable.caixa3)
            13 -> rect = BitmapFactory.decodeResource(v.resources, R.drawable.caixa4)

            20 -> rect = BitmapFactory.decodeResource(v.resources, R.drawable.sofa1)
            21 -> rect = BitmapFactory.decodeResource(v.resources, R.drawable.sofa2)
        }

        GameThread.canvas?.drawBitmap(rect,pxInit.toFloat(),pyInit.toFloat(),null)
    }

    companion object {
        var MurVer = 1
        var MurHor = 2
        var MurDownToLeft = 3
        var MurDownToRight = 4
        var MurUpToLeft = 5
        var MurUpToRight = 6
        var MurHorTD = 7
        var MurHorTI = 8

        var Caixa1 = 10
        var Caixa2 = 11
        var Caixa3 = 12
        var Caixa4 = 13

        var Sofa1 = 20
        var Sofa2 = 21
    }
}