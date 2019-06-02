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

class GameView(context: Context,playActivity: PlayActivity,lvlSelected: Int): SurfaceView(context), SurfaceHolder.Callback{

    private var playActivity: PlayActivity = playActivity
    private var thread: GameThread
    private var screenW: Int
    private var blockSize: Int
    private var obsArray: ArrayList<AbstObstaculo> = ArrayList()

    init {
        holder.addCallback(this)

        val matrix: DisplayMetrics = resources.displayMetrics

        screenW = matrix.widthPixels

        blockSize = screenW.div(25)
        blockSize = (blockSize.div(5)).times(5)

        renderMap(lvlSelected)

        thread = GameThread(holder,this,obsArray)
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
        playActivity.finestraDerrota()
    }

    fun youWin(){
        playActivity.finestraVictoria(getSuma())
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
        val level = LevelGallery.levels[lvlSelected].map
        var x: Int
        var y: Int
        var obs: AbstObstaculo?
        val hielos: ArrayList<AbstObstaculo> = ArrayList()
        val murs: ArrayList<AbstObstaculo> = ArrayList()
        val trampes: ArrayList<AbstObstaculo> = ArrayList()
        val altres: ArrayList<AbstObstaculo> = ArrayList()
        var pj: AbstObstaculo = Personaje(50, 50) //inicialitzem a una posició en concret per si de cas

        for (i in 0..14) {
            for (j in 0..24) {
                x = j * blockSize
                y = (i * (blockSize))
                obs = ObstacleCreator.create(level[i][j],x,y,playActivity)
                when {
                    obs is Hielo -> hielos.add(obs)
                    obs is Mur -> murs.add(obs)
                    obs is Trampa -> trampes.add(obs)
                    obs is Personaje -> pj = obs
                    obs != null -> altres.add(obs)
                }
            }
        }
        obsArray.addAll(hielos)
        obsArray.addAll(murs)
        obsArray.addAll(trampes)
        obsArray.addAll(altres)
        obsArray.add(pj)
    }

    fun getSuma(): Int{
        return playActivity.suma
    }
    fun getSolucion(): Int{
        return playActivity.solucion()
    }
    fun changeActionButtonColor(b: Boolean){
        playActivity.actionButtonColor(b)
    }
}
