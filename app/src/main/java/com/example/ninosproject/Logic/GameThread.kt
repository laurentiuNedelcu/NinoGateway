package com.example.ninosproject.Logic

import android.graphics.Canvas
import android.view.SurfaceHolder
import android.widget.Button
import com.example.ninosproject.ObstacleObject.Mur

class GameThread(surfaceHolder: SurfaceHolder, gameView: GameView,m:ArrayList<Mur>) : Thread() {
    private val FPS : Long = 30
    private var avgFPS : Double = 0.0
    private var holder: SurfaceHolder = surfaceHolder
    private var gameView: GameView = gameView
    private var gameEngine: GameEngine = GameEngine(gameView,m)
    private var isRunning: Boolean = true
    private var buttonArray: ArrayList<Button> = ArrayList<Button>(6)

    companion object { //Para poder llamar al objeto estaticamente (ex: GameThread.canvas)
        var canvas: Canvas? = null
    }

    init {
        isRunning = false
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

                    if(buttonArray[0].isPressed){
                        gameEngine.updateL()
                    }
                    if(buttonArray[1].isPressed){
                        gameEngine.updateD()
                    }
                    if(buttonArray[2].isPressed){
                        gameEngine.updateR()
                    }
                    if(buttonArray[3].isPressed){
                        gameEngine.updateU()
                    }

                    gameEngine.draw()
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

    fun addButtons(b: ArrayList<Button>){
        this.buttonArray = b
    }




}
