package com.example.ninosproject.Logic

import android.content.Context
import android.graphics.Color
import android.util.DisplayMetrics
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Button
import com.example.ninosproject.Activities.PlayActivity
import com.example.ninosproject.Data.LevelGallery
import com.example.ninosproject.ObstacleObject.*
import java.util.logging.Level

class GameView(context: Context,playActivity: PlayActivity,lvlSelected: Int): SurfaceView(context), SurfaceHolder.Callback{

    private var playActivity: PlayActivity = playActivity
    private var thread: GameThread
    private var screenW: Int
    private var blockSize: Int
    private var murArray: ArrayList<AbstObstaculo> = ArrayList()

    init {
        holder.addCallback(this)

        val matrix: DisplayMetrics = resources.displayMetrics

        screenW = matrix.widthPixels

        blockSize = screenW.div(25)
        blockSize = (blockSize.div(5)).times(5)

        renderMap(lvlSelected)

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

    fun disableButtons(){
        thread.getButtons()[0].isEnabled = false
        thread.getButtons()[1].isEnabled = false
        thread.getButtons()[2].isEnabled = false
        thread.getButtons()[3].isEnabled = false
    }

    fun enableButtons(){
        thread.getButtons()[0].isEnabled = true
        thread.getButtons()[1].isEnabled = true
        thread.getButtons()[2].isEnabled = true
        thread.getButtons()[3].isEnabled = true
    }
    private fun renderMap(lvlSelected: Int) {
        var level = LevelGallery.levels[lvlSelected].map
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
                    62->{
                        var laser = Laser(x,y)
                        murArray.add(laser)
                    }
                    63->{
                        var obs = Pendulo(x,y)
                        murArray.add(obs)
                    }
                    64->{
                        var obs = CasillaPinchitos(x,y)
                        murArray.add(obs)
                    }
                    70->{
                        var button = ResetButton(x,y,playActivity)
                        murArray.add(button)
                    }
                    14->{
                        var hielo = Hielo(x,y)
                        murArray.add(hielo)
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
