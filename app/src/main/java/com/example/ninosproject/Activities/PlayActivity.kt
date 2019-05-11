package com.example.ninosproject.Activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.*
import com.example.ninosproject.Data.AudioPlay
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R

class PlayActivity : AppCompatActivity() {


    private lateinit var sfx: String

    private lateinit var popupWindow: PopupWindow
    private var isPopupOn: Boolean = false
    private lateinit var gameView: GameView
    private lateinit var game: FrameLayout //Contindra la vista del gameView y de los botones
    private lateinit var gameButtons: RelativeLayout
    private lateinit var auxLayout: RelativeLayout
    private lateinit var leftDownRightLayout: LinearLayout
    private lateinit var upLayout: LinearLayout
    private val buttons: ArrayList<Button> = ArrayList()

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AudioPlay.playMusic(this,R.raw.megalovania,true)

        val clickButton = AudioPlay.getSoundPool().load(this,R.raw.press_button,1)
        val clickOptions = AudioPlay.getSoundPool().load(this,R.raw.pause_button,1)

        sfx = intent.getStringExtra("sfx")

        val buttonLeft = Button(this)
        buttonLeft.setBackgroundResource(android.R.drawable.btn_default)
        buttonLeft.text = "L"
        buttonLeft.id = 111222

        val buttonDown = Button(this)
        buttonDown.setBackgroundResource(android.R.drawable.btn_default)
        buttonDown.text = "D"
        buttonDown.id = 222333

        val buttonRight = Button(this)
        buttonRight.setBackgroundResource(android.R.drawable.btn_default)
        buttonRight.text = "R"
        buttonRight.id = 333444

        val buttonUp = Button(this)
        buttonUp.setBackgroundResource(android.R.drawable.btn_default)
        buttonUp.text = "U"
        buttonUp.id = 444555

        val buttonPause = Button(this)
        buttonPause.setBackgroundResource(R.drawable.pause_button)
        buttonPause.id = 555666
        buttonPause.setOnClickListener {
            if(!isPopupOn) {
                finestraPause(buttonPause, clickButton, clickOptions)
            } else {
                gameView.resume()
                AudioPlay.resumeMusic()
                isPopupOn = false
                buttonPause.setBackgroundResource(R.drawable.pause_button)
                popupWindow.dismiss()
            }
        }

        val buttonInteraction = Button(this)
        buttonInteraction.setBackgroundResource(android.R.drawable.btn_default)
        buttonInteraction.text = "A"
        buttonInteraction.id = 666777



        game = FrameLayout(this)
        gameView = GameView(this,this)
        gameButtons = RelativeLayout(this)
        auxLayout = RelativeLayout(this)
        leftDownRightLayout = LinearLayout(this)
        upLayout = LinearLayout(this)

        leftDownRightLayout.orientation = LinearLayout.HORIZONTAL
        upLayout.orientation = LinearLayout.VERTICAL

        val layoutButton: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(210, 180)
        val layoutButtonUp: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(210, 180)
        val layoutButtonPause: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(210, 180)
        val layoutButtonInt: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(210, 180)

        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        val paramsButtons: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val paramsAux: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )

        leftDownRightLayout.layoutParams = paramsButtons
        layoutButtonUp.leftMargin = 210
        upLayout.layoutParams = paramsButtons
        gameButtons.layoutParams = params
        auxLayout.layoutParams = paramsAux

        buttonLeft.layoutParams = layoutButton
        buttonDown.layoutParams = layoutButton
        buttonRight.layoutParams = layoutButton
        buttonUp.layoutParams = layoutButtonUp
        buttonPause.layoutParams = layoutButtonPause
        buttonInteraction.layoutParams = layoutButtonInt

        layoutButtonPause.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        layoutButtonPause.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        layoutButtonInt.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        layoutButtonInt.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        paramsAux.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        paramsAux.addRule(RelativeLayout.ALIGN_PARENT_LEFT)

        leftDownRightLayout.addView(buttonLeft)
        leftDownRightLayout.addView(buttonDown)
        leftDownRightLayout.addView(buttonRight)

        upLayout.addView(buttonUp)
        upLayout.addView(leftDownRightLayout)

        auxLayout.addView(upLayout)

        gameButtons.addView(auxLayout)
        gameButtons.addView(buttonPause)
        gameButtons.addView(buttonInteraction)


        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        game.addView(gameView)
        game.addView(gameButtons)

        buttons.add(buttonLeft)
        buttons.add(buttonDown)
        buttons.add(buttonRight)
        buttons.add(buttonUp)
        buttons.add(buttonInteraction)
        buttons.add(buttonPause)

        gameView.addButtons(buttons)
        setContentView(game)
    }

    val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    fun finestraPause(buttonPause: Button, clickButton: Int, clickOptions: Int) {

        AudioPlay.getSoundPool().play(clickOptions,1F,1F,2,0, 1F)
        AudioPlay.pauseMusic()
        gameView.pause()
        isPopupOn = true
        buttonPause.setBackgroundResource(R.drawable.play_button)

        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater.inflate(R.layout.activity_pause, null)

        popupWindow = PopupWindow(view, 375.dp, 335.dp, false)

        popupWindow.showAtLocation(game, Gravity.CENTER, 0, 0)

        //popupWindow.isOutsideTouchable = true
        val reprenJoc: Button = view.findViewById(R.id.repren_button)
        reprenJoc.setOnClickListener {
            AudioPlay.getSoundPool().play(clickButton,1F,1F,0,0, 1F)
            AudioPlay.resumeMusic()
            //Recuperar estat del joc i tornar a la partida.
            buttonPause.setBackgroundResource(R.drawable.pause_button)
            gameView.resume()
            isPopupOn = false
            popupWindow.dismiss()
        }
        val opcionsJoc: Button = view.findViewById(R.id.opcions_button)
        opcionsJoc.setOnClickListener {
            AudioPlay.getSoundPool().play(clickOptions,1F,1F,0,0, 1F)
            popupWindow.dismiss()
            finestraOpcions(buttonPause, clickButton, clickOptions)
        }
        val menu_joc: Button = view.findViewById(R.id.menu_button)
        menu_joc.setOnClickListener {
            AudioPlay.getSoundPool().play(clickButton,1F,1F,0,0, 1F)
            AudioPlay.stopMusic()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("sfx", sfx)
            popupWindow.dismiss()
            finish()
            startActivity(intent)
        }
    }

    fun finestraOpcions(buttonPause: Button, clickButton: Int, clickOptions: Int) {

        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater.inflate(R.layout.popup_option_menu, null)

        popupWindow = PopupWindow(view, 375.dp, 335.dp, false)
        popupWindow.showAtLocation(this.game, Gravity.CENTER, 0, 0)

        val buttonSfx: Button = view.findViewById(R.id.button_sfx)
        val button_accept: Button = view.findViewById(R.id.button_accept)
        val button_deny: Button = view.findViewById(R.id.button_deny)

        buttonSfx.text = sfx  //Esta linea peta tot el menu

        buttonSfx.setOnClickListener { sfx(buttonSfx) }

        button_accept.setOnClickListener {
            sfx = buttonSfx.text.toString()
            popupWindow.dismiss(); finestraPause(buttonPause, clickButton, clickOptions)
        }
        button_deny.setOnClickListener {
            popupWindow.dismiss()
            finestraPause(buttonPause, clickButton, clickOptions) }
    }

    fun finestraDerrota(buttonPause: Button){

        val restartButton = AudioPlay.getSoundPool().load(this, R.raw.restart_button,1)
        val clickButton = AudioPlay.getSoundPool().load(this,R.raw.press_button,1)

        AudioPlay.stopMusic()
        AudioPlay.playMusic(this,R.raw.you_lose_music,false)
        runOnUiThread(
            object : Runnable {
                override fun run() {
                    gameView.pause()
                    buttonPause.isEnabled = false
                    buttonPause.visibility = View.INVISIBLE
                    isPopupOn = true

                    val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

                    val view = inflater.inflate(R.layout.activity_you_lose, null)

                    popupWindow = PopupWindow(view, 395.dp, 335.dp, false)
                    popupWindow.showAtLocation(game, Gravity.CENTER, 0, 0)

                    val retry: Button = view.findViewById(R.id.retryButtonYL)
                    val levels: Button = view.findViewById(R.id.levelsButtonYL)

                    retry.setOnClickListener {
                        AudioPlay.getSoundPool().play(restartButton,1F,1F,0,0, 2F)
                        popupWindow.dismiss()

                        finish()
                        AudioPlay.stopMusic()
                        startActivity(intent)
                    }

                    levels.setOnClickListener {
                        AudioPlay.getSoundPool().play(clickButton,1F,1F,0,0, 1F)
                        AudioPlay.stopMusic()
                        popupWindow.dismiss()
                        finish()
                    }
                }
            }
        )

    }


    fun finestraVictoria(youWin: Int, clickButton: Int){

        AudioPlay.getSoundPool().play(youWin,1F,1F,0,0, 1F)

        runOnUiThread(
            object : Runnable {
                override fun run() {
                    gameView.pause()
                    isPopupOn = true

                    val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

                    val view = inflater.inflate(R.layout.activity_you_lose, null)

                    popupWindow = PopupWindow(view, 375.dp, 335.dp, false)
                    popupWindow.showAtLocation(game, Gravity.CENTER, 0, 0)

                    val retry: Button = view.findViewById(R.id.retryButtonYL)

                    retry.setOnClickListener {
                        //Recuperar estat del joc i tornar a la partida.
                        //buttonPause.setBackgroundResource(R.drawable.pause_button)
                        popupWindow.dismiss()

                        gameView.pause()
                        finish()
                        startActivity(intent)
                    }
                }
            }
        )
    }

    fun actionButtonColor(b: Boolean){
        runOnUiThread(
            object : Runnable {
                override fun run() {
                    if(b){
                        buttons[4].setBackgroundColor(Color.YELLOW)
                    }else{
                        buttons[4].setBackgroundResource(android.R.drawable.btn_default)
                    }
                }
            })
    }

    private fun sfx(button: Button) {
        if (button.text.equals(getString(R.string.on))) {
            button.text = getString(R.string.off)
            AudioPlay.disableSFX()
        }
        else {
            synchronized(AudioPlay){
            button.text =  getString(R.string.on)
            AudioPlay.enableSFX()
            }
        }
    }

    override fun onBackPressed() {} //Deshabilitar back button del mobil
}