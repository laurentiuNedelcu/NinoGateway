package com.example.ninosproject.ObstacleObject

import android.graphics.BitmapFactory
import com.example.ninosproject.Activities.PlayActivity
import com.example.ninosproject.Data.AudioPlay
import com.example.ninosproject.Logic.GameThread
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R

class ResetButton: AbstObstaculo, ActionObject {

    override var pxInit: Int
    override var pyInit: Int
    override var pxFinal: Int
    override var pyFinal: Int
    override var newPxInit: Int
    override var newPyInit: Int
    override var newPxFinal: Int
    override var newPyFinal: Int
    private var pulsat = false
    private var times = 0
    private var playActivity: PlayActivity

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


    constructor(px: Int, py: Int, playActivity: PlayActivity){
        this.pxInit = px
        this.pyInit = py
        this.pxFinal = px+25.dp
        this.pyFinal = py+25.dp
        this.newPxInit = px
        this.newPyInit = py
        this.newPxFinal = px+25.dp
        this.newPyFinal = py+25.dp
        this.playActivity = playActivity
    }

    override fun draw(v: GameView) {
        if(!pulsat){
            var p = BitmapFactory.decodeResource(v.resources, R.drawable.reset_button)
            GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
        }else if(pulsat){
            if(times>=0 && times<=2) {
                if (times == 0){
                    AudioPlay.playMusicAux(playActivity,R.raw.press_button)
                }
                var p = BitmapFactory.decodeResource(v.resources, R.drawable.reset_button_pulsat)
                times += 1
                if(times == 3) {
                    times = 0
                    pulsat = false
                }
                GameThread.canvas?.drawBitmap(p, pxInit.toFloat(), pyInit.toFloat(), null)
            }
        }
    }

    override fun action(v: GameView) {
        pulsat = true
        playActivity.resetPuntuacio()
        playActivity.updateTextViewSuma()
    }

}