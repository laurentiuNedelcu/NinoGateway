package com.example.ninosproject.Logic

import android.content.Context
import android.graphics.*
import android.util.DisplayMetrics
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Button
import com.example.ninosproject.R

class GameView(context: Context): SurfaceView(context), SurfaceHolder.Callback{

    private var thread: GameThread
    private var circle: Bitmap
    private var px: Float
    private var py: Float
    private var screenW: Int
    private var blockSize: Int
    private var paint: Paint
    init {
        holder.addCallback(this)
        thread = GameThread(holder,this)
        circle = BitmapFactory.decodeResource(resources, R.drawable.circle)
        px = 0.0F
        py = 0.0F
        isFocusable = true
        /*****************************************/
        val matrix: DisplayMetrics = resources.displayMetrics
        screenW = matrix.widthPixels
        blockSize = screenW.div(17)
        blockSize = (blockSize.div(5)).times(5)
        paint = Paint()
        paint.color = Color.WHITE


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
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        canvas?.drawBitmap(circle,px,py,null)
        drawMap(canvas!!)
    }

    fun updateR(){
        this.px = px+5
    }

    fun addButtons(b: ArrayList<Button>){
        thread.addButtons(b)
    }

    fun startGame(){
        thread.start()
    }

    private fun drawMap(canvas: Canvas) {
        //paint.setColor(Color.BLUE)
        //paint.setStrokeWidth(2.5f)
        paint.strokeWidth = 2.5f
        var x: Int
        var y: Int
        for (i in 0..17) {
            for (j in 0..16) {
                x = j * blockSize
                y = i * blockSize
                if (level[i][j] and 1 != 0)
                // draws left
                    canvas.drawLine(x.toFloat(), y.toFloat(), x.toFloat(), (y + blockSize - 1).toFloat(), paint)

                if (level[i][j] and 2 != 0)
                // draws top
                    canvas.drawLine(x.toFloat(), y.toFloat(), (x + blockSize - 1).toFloat(), y.toFloat(), paint)

                if (level[i][j] and 4 != 0)
                // draws right
                    canvas.drawLine(
                        (x + blockSize).toFloat(),
                        y.toFloat(),
                        (x + blockSize).toFloat(),
                        (y + blockSize - 1).toFloat(),
                        paint
                    )

                if (level[i][j] and 8 != 0)
                // draws bottom
                    canvas.drawLine(
                        x.toFloat(),
                        (y + blockSize).toFloat(),
                        (x + blockSize - 1).toFloat(),
                        (y + blockSize).toFloat(),
                        paint
                    )
            }
        }
        paint.color = Color.WHITE
    }

    internal val level = arrayOf(
        intArrayOf(19, 26, 26, 18, 26, 26, 26, 26, 26, 26, 26, 26, 26, 18, 26, 26, 22),
        intArrayOf(21, 0, 0, 21, 0, 0, 0, 21, 0, 0, 0, 0, 0, 21, 0, 0, 21),
        intArrayOf(1, 26, 26, 16, 26, 18, 26, 24, 26, 24, 26, 18, 26, 16, 26, 26, 20),
        intArrayOf(21, 26, 26, 20, 0, 25, 26, 22, 0, 19, 26, 28, 0, 17, 26, 26, 21),
        intArrayOf(1, 0, 0, 21, 0, 0, 0, 21, 0, 21, 0, 0, 0, 21, 0, 0, 20),
        intArrayOf(21, 0, 0, 21, 0, 19, 26, 24, 26, 24, 26, 22, 0, 21, 0, 0, 20),
        intArrayOf(21, 0, 26, 16, 26, 20, 0, 0, 0, 0, 0, 17, 26, 16, 26, 26, 21),
        intArrayOf(25, 26, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 26, 28)
    )

}
