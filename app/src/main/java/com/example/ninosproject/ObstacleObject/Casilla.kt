package com.example.ninosproject.ObstacleObject

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.ninosproject.Activities.PlayActivity
import com.example.ninosproject.Data.AudioPlay
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R

class Casilla : AbstObstaculo {
    override var newPxInit: Int = 0
    override var newPyInit: Int = 0
    override var newPxFinal: Int = 0
    override var newPyFinal: Int  = 0
    override var pxInit: Int
    override var pyInit: Int
    override var pxFinal: Int = 0
    override var pyFinal: Int = 0
    var valor : Int
    var pressed: Boolean
    var alreadyPressed: Boolean = false
    var playActivity: PlayActivity

    constructor(px: Int, py: Int, valor : Int, playActivity: PlayActivity){
        pxInit = px
        pyInit = py
        pxFinal = px+50.dp
        pyFinal = py+50.dp
        newPxInit = px
        newPyInit = py
        newPxFinal = px+50.dp
        newPyFinal = py+50.dp
        this.valor = valor
        pressed = false
        this.playActivity = playActivity
    }


    override fun draw(v: GameView) {
        val p: Bitmap
        if(!pressed){
            when(valor){
                1-> {
                    p = BitmapFactory.decodeResource(v.resources, R.drawable.casilla_1)
                    GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
                }
                2->{
                    p = BitmapFactory.decodeResource(v.resources, R.drawable.casilla_2)
                    GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
                }
                3->{
                    p = BitmapFactory.decodeResource(v.resources, R.drawable.casilla_3)
                    GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
                }
            }
        }else{
            if(!alreadyPressed){
                AudioPlay.playMusicAux(playActivity,R.raw.switch_pissed)
                /*
                val casillaPissed = AudioPlay.getSoundPool().load(playActivity,R.raw.switch_pissed,1)
                AudioPlay.getSoundPool().play(casillaPissed,1F,1F,0,0, 1F)
                */
            }
            when(valor){
                1->{
                    p = BitmapFactory.decodeResource(v.resources, R.drawable.casilla_pressed_1)
                    GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
                    if (!alreadyPressed){
                        alreadyPressed = true
                        playActivity.setPuntuacio(1)
                        playActivity.updateTextViewSuma()
                    }
                }
                2->{
                    p = BitmapFactory.decodeResource(v.resources, R.drawable.casilla_pressed_2)
                    GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
                    if (!alreadyPressed){
                        alreadyPressed = true
                        playActivity.setPuntuacio(2)
                        playActivity.updateTextViewSuma()
                    }
                }
                3->{
                    p = BitmapFactory.decodeResource(v.resources, R.drawable.casilla_pressed_3)
                    GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
                    if (!alreadyPressed){
                        alreadyPressed = true
                        playActivity.setPuntuacio(3)
                        playActivity.updateTextViewSuma()
                    }
                }
            }
        }
    }
}
