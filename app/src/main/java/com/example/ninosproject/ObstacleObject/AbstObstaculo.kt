package com.example.ninosproject.ObstacleObject

import android.content.res.Resources
import com.example.ninosproject.Logic.GameView

abstract class AbstObstaculo {

    abstract var pxInit: Int
    abstract var pyInit: Int
    abstract var pxFinal: Int
    abstract var pyFinal: Int
    abstract var newPxInit: Int
    abstract var newPyInit: Int
    abstract var newPxFinal: Int
    abstract var newPyFinal: Int
    abstract fun draw(v: GameView)

    val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt() // Metode per passar de density independent pixels (dp) a pixels

}
