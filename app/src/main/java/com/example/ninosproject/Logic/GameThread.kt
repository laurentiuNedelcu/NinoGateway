package com.example.ninosproject.Logic

import android.graphics.Canvas
import android.view.SurfaceHolder

class GameThread(surfaceHolder: SurfaceHolder, gameView: GameView) : Thread() {
    private val FPS : Long = 30
    private var avgFPS : Double = 0.0
    private var holder: SurfaceHolder = surfaceHolder
    private var gameView: GameView = gameView
    private var isRunning: Boolean = false

    companion object { //Para poder llamar al objeto estaticamente (ex: GameThread.canvas)
        var canvas: Canvas? = null
    }

    init {
        isRunning = true
    }

    override fun run(){
        var startTime: Long
        var timeMilis: Long
        var waitMilis: Long
        var totalTime: Long = 0
        var frameCount: Long = 0
        val targetTime: Long = 1000.div(FPS)

        while(isRunning){
            startTime = System.nanoTime()
            canvas = null
            try{
                canvas = this.holder.lockCanvas()
                synchronized(holder){ //Este es el loop del juego, lo demas es para prevenir errores
                    //this.gameView.update(); //implementarlo
                    this.gameView.draw(canvas) //implementarlo
                }
            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                if(canvas!=null){
                    try{
                        holder.unlockCanvasAndPost(canvas)
                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                }
            }

            timeMilis = (System.nanoTime().minus(startTime)).div(1000000)
            waitMilis = targetTime.minus(timeMilis)

            try{
                sleep(waitMilis)
            }catch (e: Exception){
                e.printStackTrace()
            }

            totalTime += System.nanoTime().minus(startTime)
            frameCount++
            if(frameCount == FPS){
                avgFPS = (100/((totalTime/frameCount)/1000000)).toDouble()
                frameCount = 0
                totalTime = 0
                System.out.println(FPS)
            }
        }
    }

    fun setRunning(isRunning: Boolean){
        this.isRunning = isRunning
    }
}
