package com.example.ninosproject.ObstacleObject

import com.example.ninosproject.Activities.PlayActivity

object ObstacleCreator{

    private val MUR_VER = 1
    private val MUR_HOR = 2
    private val MUR_DOWN_TO_LEFT = 3
    private val MUR_DOWN_TO_RIGHT = 4
    private val MUR_UP_TO_LEFT= 5
    private val MUR_UP_TO_RIGHT = 6
    private val MUR_HOR_TD= 7
    private val MUR_HOR_TI= 8

    private val CAIXA = 10

    private val SOFA = 20

    private val CARTEL = 30

    private val CASILLA_1 = 41
    private val CASILLA_2 = 42
    private val CASILLA_3 = 43

    private val PUERTA = 50

    private val CUCHILLA = 60
    private val BOLA= 61
    private val LASER= 62
    private val PENDULO = 63
    private val CASILLA_PINCHITOS= 64
    private val HIELO= 65

    private val RESET = 70

    fun create(type: Int, x: Int, y: Int, context: PlayActivity): AbstObstaculo? {
        var obs: AbstObstaculo? = null
        when (type) {
            -1 ->{
                obs = Personaje(x,y)
            }
            MUR_HOR -> {
                obs = Mur(x, y, MUR_HOR)
            }
            MUR_VER -> {
                obs = Mur(x, y, MUR_VER)
            }
            MUR_UP_TO_LEFT -> {
                obs = Mur(x, y, MUR_UP_TO_LEFT)
            }
            MUR_DOWN_TO_RIGHT -> {
                obs = Mur(x, y, MUR_DOWN_TO_RIGHT)
            }
            MUR_DOWN_TO_LEFT -> {
                obs = Mur(x, y, MUR_DOWN_TO_LEFT)
            }
            MUR_UP_TO_RIGHT -> {
                obs = Mur(x, y, MUR_UP_TO_RIGHT)
            }
            MUR_HOR_TD -> {
                obs = Mur(x, y, MUR_HOR_TD)
            }
            MUR_HOR_TI-> {
                obs = Mur(x, y, MUR_HOR_TI)
            }
            CAIXA -> {
                obs = Mur(x, y, CAIXA)
            }
            SOFA -> {
                obs = Mur(x, y, SOFA)
            }
            CARTEL -> {
                obs = Cartel(x, y)
            }
            CASILLA_1->{
                obs = Casilla(x, y, 1, context)
            }
            CASILLA_2->{
                obs = Casilla(x, y, 2, context)
            }
            CASILLA_3->{
                obs = Casilla(x, y, 3, context)
            }
            PUERTA->{
                obs = Puerta(x, y,context)
            }
            CUCHILLA->{
                obs = Cuchilla(x, y)
            }
            BOLA->{
                obs = Bola(x, y)
            }
            LASER->{
                obs = Laser(x, y)
            }
            PENDULO->{
                obs = Pendulo(x, y)
            }
            CASILLA_PINCHITOS->{
                obs = CasillaPinchitos(x, y)
            }
            HIELO->{
                obs = Hielo(x, y)
            }
            RESET->{
                obs = ResetButton(x, y, context)
            }
        }
        return obs
    }
}
