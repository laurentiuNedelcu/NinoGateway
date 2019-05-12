package com.example.ninosproject.Logic

import android.content.Context
import android.graphics.Color
import android.util.DisplayMetrics
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Button
import com.example.ninosproject.Activities.PlayActivity
import com.example.ninosproject.ObstacleObject.*

class GameView(context: Context,playActivity: PlayActivity): SurfaceView(context), SurfaceHolder.Callback{

    private var playActivity: PlayActivity = playActivity
    private var thread: GameThread
    private var screenW: Int
    private var blockSize: Int
    private var murArray: ArrayList<AbstObstaculo> = ArrayList()

    internal val level = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 10, 0, 1, 0, 0, 0, 1, 30, 0, 0, 0, 0, 43, 0, 0, 0, 1, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 70, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
        intArrayOf(2, 2, 2, 7, 0, 0, 0, 8, 2, 5, 0, 0, 0, 6, 2, 7, 0, 0, 0, 8, 2, 2, 2, 5, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 61, 0, 41, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(2, 2, 2, 7, 0, 0, 0, 8, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 61, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 20, 1, 0, 42, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0),
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
        isFocusable = true


    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        thread.setRunning(false)
    }

    fun pause(){
        thread.pause()
    }

    fun resume(){
        thread.resumeThread()
    }

    fun finish(){
        thread.setRunning(false)
        thread.interrupt()
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        thread.setRunning(true)
        thread.start()
    }

    fun draw(obs: ArrayList<AbstObstaculo>) {
        super.draw(GameThread.canvas)
        GameThread.canvas?.drawColor(Color.WHITE)
        for (i in obs){
            i.draw(this)
        }
    }

    fun addButtons(b: ArrayList<Button>){
        thread.addButtons(b)
    }

    fun youLose() {
        playActivity.finestraDerrota(thread.getButtons()[5])
    }

    fun youWin(){
        playActivity.finestraVictoria(thread.getButtons()[5])
    }

    fun showEnigma(){
        playActivity.popup_enigma(thread.getButtons()[5])
    }

    private fun renderMap() {
        var x: Int
        var y: Int
        var mur: Mur
        for (i in 0..14) {
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
                    Mur.MurHorTD -> {
                        mur = Mur(x, y, Mur.MurHorTD)
                        murArray.add(mur)
                    }
                    Mur.MurHorTI-> {
                        mur = Mur(x, y, Mur.MurHorTI)
                        murArray.add(mur)
                    }
                    Mur.Caixa -> {
                        mur = Mur(x, y, Mur.Caixa)
                        murArray.add(mur)
                    }
                    Mur.Sofa -> {
                        mur = Mur(x, y, Mur.Sofa)
                        murArray.add(mur)
                    }
                    30 -> {
                        var cart = Cartel(x,y)
                        murArray.add(cart)
                    }
                    41->{
                        var casilla = Casilla(x,y,1,playActivity)
                        murArray.add(casilla)
                    }
                    42->{
                        var casilla = Casilla(x,y,2,playActivity)
                        murArray.add(casilla)
                    }
                    43->{
                        var casilla = Casilla(x,y,3,playActivity)
                        murArray.add(casilla)
                    }
                    50->{
                        var puerta = Puerta(x,y)
                        murArray.add(puerta)
                    }
                    60->{
                        var cuchilla = Cuchilla(x,y)
                        murArray.add(cuchilla)
                    }
                    61->{
                        var bola = Bola(x,y)
                        murArray.add(bola)
                    }
                    70->{
                        var button = ResetButton(x,y,playActivity)
                        murArray.add(button)
                    }
                }
            }
        }
    }

    fun getSuma(): Int{
        return playActivity.suma
    }
    fun changeActionButtonColor(b: Boolean){
        playActivity.actionButtonColor(b)
    }
}
