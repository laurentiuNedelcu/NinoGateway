package com.example.ninosproject.Activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.FrameLayout
import android.widget.PopupWindow
import android.widget.RelativeLayout
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R

class PlayActivity : AppCompatActivity() {

    private lateinit var vibration_state: String

    //inicialitzem la finestra
    private lateinit var popupWindow : PopupWindow
    private lateinit var pause : Button
    private var isPopupOn : Boolean = false

    //Prova per al cercle
    private lateinit var gameView: GameView
    private lateinit var game: FrameLayout //Contindra la vista del gameView y de los botones
    private lateinit var gameButtons: RelativeLayout

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //**********************************PROVA**************************************************
        gameView = GameView(this)
        game = FrameLayout(this)
        gameButtons = RelativeLayout(this)

        //Creamos los botones
        val buttonRight: Button = Button(this)
        buttonRight.text = "->"
        buttonRight.id = 123456 //Hay que ponerles un id diferente para diferenciarlos de los default buttons (que tienen el mismo id)

        val buttonLeft: Button = Button(this)
        buttonLeft.text = "<-"
        buttonLeft.id = 654321

        //Definimos los parametros de diseño de los botones para envolverlos en el layout
        val layoutButtonRight: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT)

        val layoutButtonLeft: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT)

        //Cuando tengamos los layouts de los botones hacemos otro para envolver a estos dos layouts
        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT)

        //Ahora le ponemos este layout al RelativeLayout (que lo he anomenado gameButtons)
        gameButtons.layoutParams = params
        //Y le añadimos los botones
        gameButtons.addView(buttonRight)
        gameButtons.addView(buttonLeft)

        //Hasta ahora tenemos creado los layouts, y ahora decidimos donde queremos ponerlos
        layoutButtonLeft.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        layoutButtonLeft.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        buttonLeft.layoutParams = layoutButtonLeft

        layoutButtonRight.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        layoutButtonRight.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        buttonRight.layoutParams = layoutButtonRight

        //**********************************PROVA**************************************************

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //PROVA
        //Como he dicho antes el FrameLayout va a contener las dos "vistas"
        game.addView(gameView)
        game.addView(gameButtons)
        setContentView(game)

        //setContentView(GameView(this))

/*        val tmp_win: Button = findViewById(R.id.tmpwinbutton)
        val tmp_lose: Button = findViewById(R.id.tmplosebutton)
        pause = findViewById(R.id.pauseButton)
        vibration_state = intent.getStringExtra("vibration")

        tmp_win.setOnClickListener {
            if(!isPopupOn) {
                pause.setBackground(ColorDrawable(android.graphics.Color.TRANSPARENT))
                isPopupOn = true
                finestraWin()
            }
        }
        tmp_lose.setOnClickListener {
            if(!isPopupOn) {
                pause.setBackground(ColorDrawable(android.graphics.Color.TRANSPARENT))
                isPopupOn = true
                finestraLose()
            }
        }
        pause.setOnClickListener {
            if(!isPopupOn) {
                pause.setBackgroundResource(R.drawable.play_button)
                isPopupOn = true
                finestraPause()
            }
        }

*/
    }


    val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt() // Metode per passar de density independent pixels (dp) a pixels

    private fun finestraWin() {
        //Layout per fer visible el popup
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //Fem visible el layout però no el mostrem
        val view = inflater.inflate(R.layout.activity_you_win, null)

        popupWindow = PopupWindow(
            view, // Layout inflat que volem mostrar
            375.dp, // Width (dp transformat a pixel)
            335.dp, // Heigth (dp transformat a pixel)
            false
        )
        //mostrem la finestra amb el layout
        popupWindow.showAtLocation(findViewById(R.id.play_activity), Gravity.CENTER, 0, 0)

        var retry_level: Button = view.findViewById(R.id.retryButtonYW)
        var levels: Button = view.findViewById(R.id.levelsbuttonYW)
        var next_level: Button = view.findViewById(R.id.nextButtonYW)


        retry_level.setOnClickListener {
            isPopupOn = false
            pause.setBackgroundResource(R.drawable.pause_button)
            popupWindow.dismiss()
        }


        levels.setOnClickListener {
            //val intent: Intent = Intent(this, LevelActivity::class.java)
            //startActivity(intent)
            finish()
        }


        next_level.setOnClickListener {
            //val intent: Intent = Intent(this, seguent nivell)//falta per implementar
            //startActivity(intent)
            isPopupOn = false
            pause.setBackgroundResource(R.drawable.pause_button)
            popupWindow.dismiss()
        }


    }

    private fun finestraLose() {
        //Layout per fer visible el popup
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //Fem visible el layout però no el mostrem
        val view = inflater.inflate(R.layout.activity_you_lose, null)

        //inicialitzem la finestra
        popupWindow = PopupWindow(
            view, // Layout inflat que volem mostrar
            375.dp, // Width (dp transformat a pixel)
            335.dp, // Heigth (dp transformat a pixel)
            false
        )

        //mostrem la finestra amb el layout
        popupWindow.showAtLocation(findViewById(R.id.play_activity), Gravity.CENTER, 0, 0)

        var retry_level: Button = view.findViewById(R.id.retryButtonYL)
        retry_level.setOnClickListener {
            isPopupOn = false
            pause.setBackgroundResource(R.drawable.pause_button)
            popupWindow.dismiss()
        }

        var levels: Button = view.findViewById(R.id.levelsButtonYL)
        levels.setOnClickListener {
            //val intent: Intent = Intent(this, LevelActivity::class.java)
            //startActivity(intent)
            finish()
        }
    }

    private fun finestraPause() {
        //Layout per fer visible el popup
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //Fem visible el layout però no el mostrem
        val view = inflater.inflate(R.layout.activity_pause, null)

        //inicialitzem la finestra
        popupWindow = PopupWindow(
            view, // Layout inflat que volem mostrar
            375.dp, // Width (dp transformat a pixel)
            335.dp, // Heigth (dp transformat a pixel)
            false
        )

        //mostrem la finestra amb el layout
        popupWindow.showAtLocation(findViewById(R.id.play_activity), Gravity.CENTER, 0, 0)

        popupWindow.isOutsideTouchable = true
        var repren_joc: Button = view.findViewById(R.id.repren_button)
        repren_joc.setOnClickListener {
            //Recuperar estat del joc i tornar a la partida.
            pause.setBackgroundResource(R.drawable.pause_button)
            isPopupOn = false
            popupWindow.dismiss()
        }
        var opcions_joc: Button = view.findViewById(R.id.opcions_button)
        opcions_joc.setOnClickListener {
            //val intent : Intent = Intent(this, falta classe opcions)
            //startActivity(intent)
            popupWindow.dismiss()
            finestraOpcions()
        }
        var menu_joc: Button = view.findViewById(R.id.menu_button)
        menu_joc.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            intent.putExtra("vibration",vibration_state)
            startActivity(intent) //torna al menu principal, no al menu dels nivells
        }
    }

    fun finestraOpcions() {
        //A partir d'aqui generem la finestra popup on es trobarán totes les opcions

        //Layout per fer visible el popup
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //Fem visible el layout però no el mostrem
        val view = inflater.inflate(R.layout.popup_option_menu, null)

        //inicialitzem la finestra
        popupWindow = PopupWindow(
            view, // Layout inflat que volem mostrar
            375.dp, // Width (dp transformat a pixel)
            335.dp, // Heigth (dp transformat a pixel)
            false
        )

        //mostrem la finestra amb el layout
        popupWindow.showAtLocation(findViewById(R.id.play_activity), Gravity.CENTER, 0, 0)

        var button_vibracio: Button = view.findViewById(R.id.button_vibracio)
        var button_accept: Button = view.findViewById(R.id.button_accept)
        var button_deny: Button = view.findViewById(R.id.button_deny)

       button_vibracio.text = vibration_state

        var estat_anterior = vibration_state
        button_vibracio.setOnClickListener { vibracio(button_vibracio) }

        button_accept.setOnClickListener { vibration_state = button_vibracio.text.toString(); popupWindow.dismiss(); finestraPause() }
        button_deny.setOnClickListener { popupWindow.dismiss(); finestraPause() }
    }

    fun vibracio(button: Button) {
        if (button.text.equals(getString(R.string.off))) button.text = getString(R.string.on)
        else button.text = getString(R.string.off)
    }


    override fun onBackPressed() {} //Deshabilitar back button del mobil

}
