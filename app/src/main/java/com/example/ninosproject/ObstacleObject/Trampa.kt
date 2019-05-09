package com.example.ninosproject.ObstacleObject

import com.example.ninosproject.Logic.GameView

abstract class Trampa : AbstObstaculo(){
    abstract fun update(px: Int, py: Int)
    abstract fun newPosition():ArrayList<Int>
    abstract fun draw(v: GameView)
}
