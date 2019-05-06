package com.example.ninosproject.Logic

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.ninosproject.R

class GameView(context: Context): SurfaceView(context), SurfaceHolder.Callback{

    private var thread: GameThread
    private var circle: Bitmap
    private var px: Float
    private var py: Float
    init {
        holder.addCallback(this)
        thread = GameThread(holder,this)
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

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        canvas?.drawBitmap(circle,px,py,null)
    }

}
