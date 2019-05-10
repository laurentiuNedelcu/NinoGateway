package com.example.ninosproject.ObstacleObject

import com.example.ninosproject.Logic.GameView

abstract class Trampa : AbstObstaculo(){
    abstract fun update()
    abstract fun newPosition()
}
