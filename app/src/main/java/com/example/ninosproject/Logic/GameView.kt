package com.example.ninosproject.Logic

import android.content.Context
import android.graphics.*
import android.util.DisplayMetrics
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Button
import com.example.ninosproject.Data.Personaje
import com.example.ninosproject.ObstacleObject.Mur
import com.example.ninosproject.ObstacleObject.Trampa
import com.example.ninosproject.R

class GameView(context: Context): SurfaceView(context), SurfaceHolder.Callback{

    private var thread: GameThread
    private var circle: Bitmap
    private var px: Float
    private var py: Float
    private var screenW: Int
    private var blockSize: Int
    private var murArray: ArrayList<Mur> = ArrayList<Mur>()

    internal val level = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0),
        intArrayOf(2, 2, 2, 2, 0, 0, 0, 2, 2, 5, 0, 0, 0, 6, 2, 2, 2, 0, 0, 0, 2, 5, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(2, 2, 2, 2, 0, 0, 0, 2, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    )

    init {
        holder.addCallback(this)

        val matrix: DisplayMetrics = resources.displayMetrics

        screenW = matrix.widthPixels

        //blockSize = screenW.div(25.dp)

        blockSize = screenW.div(25)
        blockSize = (blockSize.div(5)).times(5)

        renderMap()

        thread = GameThread(holder,this,murArray)
        circle = BitmapFactory.decodeResource(resources, R.drawable.circle)
        px = 0.0F
        py = 0.0F
        isFocusable = true


    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        val retry = true
        while (retry){
            try {
                thread.setRunning(false)
                thread.join()
            } catch (e: InterruptedException){
                e.printStackTrace()
            }
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        thread.setRunning(true)
        thread.start()
    }

    fun draw(personaje: Personaje, traps: ArrayList<Trampa>) {
        super.draw(GameThread.canvas)
        GameThread.canvas?.drawColor(Color.WHITE)
        personaje.draw(this)
        drawMap(GameThread.canvas!!)
        for (i in traps){
            i.draw(this)
        }
    }

    fun addButtons(b: ArrayList<Button>){
        thread.addButtons(b)
    }

    fun startGame(){
        thread.setRunning(true)
    }


    private fun drawMap(canvas: Canvas) {
        for (i in murArray){
            i.draw(this,canvas)
        }
    }


    private fun renderMap() {
        var x: Int
        var y: Int
        var mur: Mur
        for (i in 0..13) {
            for (j in 0..24) {
                x = j * blockSize
                y = (i * (blockSize))
                when(level[i][j]) {
                     Mur.MurHor -> {
                        mur = Mur(x, y, Mur.MurHor)
                        murArray.add(mur)
                    }
                    Mur.MurVer -> {
                        mur = Mur(x, y, Mur.MurVer)
                        murArray.add(mur)
                    }
                    Mur.MurUpToLeft -> {
                        mur = Mur(x, y, Mur.MurUpToLeft)
                        murArray.add(mur)
                    }
                    Mur.MurUpToRight -> {
                        mur = Mur(x, y, Mur.MurUpToRight)
                        murArray.add(mur)
                    }
                    Mur.MurDownToLeft -> {
                        mur = Mur(x, y, Mur.MurDownToLeft)
                        murArray.add(mur)
                    }
                    Mur.MurDownToRight -> {
                        mur = Mur(x, y, Mur.MurDownToRight)
                        murArray.add(mur)
                    }
                }
            }
        }
    }
}
