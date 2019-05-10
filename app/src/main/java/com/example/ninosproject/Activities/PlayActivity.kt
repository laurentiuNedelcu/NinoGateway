package com.example.ninosproject.Activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.system.Os.remove
import android.view.*
import android.widget.*
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R
import java.lang.Exception
import kotlin.concurrent.thread

class PlayActivity : AppCompatActivity() {


    private lateinit var vibration: String

    private lateinit var popupWindow: PopupWindow
    private var isPopupOn: Boolean = false
    private lateinit var gameView: GameView
    private lateinit var game: FrameLayout //Contindra la vista del gameView y de los botones
    private lateinit var gameButtons: RelativeLayout
    private lateinit var auxLayout: RelativeLayout
    private lateinit var leftDownRightLayout: LinearLayout
    private lateinit var upLayout: LinearLayout

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vibration = intent.getStringExtra("vibration")

        val buttonLeft = Button(this)
        buttonLeft.text = "L"
        buttonLeft.id = 111222

        val buttonDown = Button(this)
        buttonDown.text = "D"
        buttonDown.id = 222333

        val buttonRight = Button(this)
        buttonRight.text = "R"
        buttonRight.id = 333444

        val buttonUp = Button(this)
        buttonUp.text = "U"
        buttonUp.id = 444555

        val buttonPause = Button(this)
        buttonPause.setBackgroundResource(R.drawable.pause_button)
        buttonPause.id = 555666
        buttonPause.setOnClickListener {
            if(!isPopupOn) {
                finestraPause(buttonPause)
            } else {
                gameView.resume()
                isPopupOn = false
                buttonPause.setBackgroundResource(R.drawable.pause_button)
                popupWindow.dismiss()
            }
        }

        val buttonInteraction = Button(this)
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

        val buttons: ArrayList<Button> = ArrayList()
        buttons.add(buttonLeft)
        buttons.add(buttonDown)
        buttons.add(buttonRight)
        buttons.add(buttonUp)
        buttons.add(buttonInteraction)
        buttons.add(buttonPause)

        gameView.addButtons(buttons)
        setContentView(game)
        val restart = Intent(this, PlayActivity::class.java)
    }

    val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    fun finestraPause(buttonPause: Button) {

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
            //Recuperar estat del joc i tornar a la partida.
            buttonPause.setBackgroundResource(R.drawable.pause_button)
            gameView.resume()
            isPopupOn = false
            popupWindow.dismiss()
        }
        val opcionsJoc: Button = view.findViewById(R.id.opcions_button)
        opcionsJoc.setOnClickListener {
            popupWindow.dismiss()
            finestraOpcions(buttonPause)
        }
        val menu_joc: Button = view.findViewById(R.id.menu_button)
        menu_joc.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("vibration", vibration)
            popupWindow.dismiss()
            finish()
        }
    }

    fun finestraOpcions(buttonPause: Button) {

        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater.inflate(R.layout.popup_option_menu, null)

        popupWindow = PopupWindow(view, 375.dp, 335.dp, false)
        popupWindow.showAtLocation(this.game, Gravity.CENTER, 0, 0)

        val button_vibracio: Button = view.findViewById(R.id.button_vibracio)
        val button_accept: Button = view.findViewById(R.id.button_accept)
        val button_deny: Button = view.findViewById(R.id.button_deny)

        button_vibracio.text = vibration  //Esta linea peta tot el menu

        button_vibracio.setOnClickListener { vibracio(button_vibracio) }

        button_accept.setOnClickListener {
            vibration = button_vibracio.text.toString(); popupWindow.dismiss(); finestraPause(buttonPause)
        }
        button_deny.setOnClickListener { popupWindow.dismiss(); finestraPause(buttonPause) }
    }

    fun finestraDerrota(buttonPause: Button){

        runOnUiThread(
            object : Runnable {
                override fun run() {

                    gameView.pause()
                    buttonPause.isEnabled = false
                    buttonPause.visibility = View.INVISIBLE
                    isPopupOn = true

                    val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

                    val view = inflater.inflate(R.layout.activity_you_lose, null)

                    popupWindow = PopupWindow(view, 375.dp, 335.dp, false)
                    popupWindow.showAtLocation(game, Gravity.CENTER, 0, 0)

                    val retry: Button = view.findViewById(R.id.retryButtonYL)
                    val levels: Button = view.findViewById(R.id.levelsButtonYL)

                    retry.setOnClickListener {
                        //Recuperar estat del joc i tornar a la partida.
                        //buttonPause.setBackgroundResource(R.drawable.pause_button)
                        popupWindow.dismiss()

                        finish()
                        startActivity(intent)
                    }

                    levels.setOnClickListener {
                    popupWindow.dismiss()
                        finish()
                    }
                }
            }
        )

    }


    fun finestraVictoria(){

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

    fun vibracio(button: Button) {
        if (button.text.equals(getString(R.string.off))) button.text = getString(R.string.on)
        else button.text = getString(R.string.off)
    }

    override fun onBackPressed() {} //Deshabilitar back button del mobil
}