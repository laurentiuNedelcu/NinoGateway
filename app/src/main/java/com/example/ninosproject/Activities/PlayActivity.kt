package com.example.ninosproject.Activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.view.KeyEvent.KEYCODE_HOME
import android.widget.*
import com.example.ninosproject.Data.*
import com.example.ninosproject.Logic.GameView
import com.example.ninosproject.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class PlayActivity : AppCompatActivity() {


    private lateinit var sfx: String
    private lateinit var musica: String

    private lateinit var popupWindow: PopupWindow
    private var isPopupOn: Boolean = false
    private lateinit var gameView: GameView
    private lateinit var game: FrameLayout //Contindra la vista del gameView y de los botones
    private lateinit var gameButtons: RelativeLayout
    private lateinit var auxLayout: RelativeLayout
    private lateinit var leftDownRightLayout: LinearLayout
    private lateinit var upLayout: LinearLayout
    private lateinit var scoreLayout: LinearLayout
    private lateinit var puntuacio: TextView
    private lateinit var sumaText: TextView
    private lateinit var level: Nivel
    private lateinit var buttonPause: Button
    private val buttons: ArrayList<Button> = ArrayList()
    private var lvlSelected: Int = -1
    var suma: Int = 0

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getSfxMusica()

        AudioPlay.playMusic(this, R.raw.wonderful_world, true)

        val clickButton = R.raw.press_button
        val clickOptions = R.raw.pause_button

        lvlSelected = intent.getStringExtra("level").toInt()
        level = LevelGallery.levels[lvlSelected]

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

        buttonPause = Button(this)
        buttonPause.setBackgroundResource(R.drawable.pause_button)
        buttonPause.id = 555666
        buttonPause.setOnClickListener {
            if (!isPopupOn) {
                finestraPause(clickButton, clickOptions)
            } else {
                AudioPlay.playSfx(this, R.raw.resume_button)
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
        gameView = GameView(this, this, lvlSelected)
        gameButtons = RelativeLayout(this)
        auxLayout = RelativeLayout(this)
        leftDownRightLayout = LinearLayout(this)
        upLayout = LinearLayout(this)
        scoreLayout = LinearLayout(this)

        puntuacio = TextView(this)
        puntuacio.text = getString(R.string.zero)
        puntuacio.textSize = 20F
        puntuacio.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        puntuacio.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        puntuacio.setTextColor(Color.BLACK)

        sumaText = TextView(this)
        sumaText.text = getString(R.string.suma)
        sumaText.textSize = 20F
        sumaText.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        sumaText.setTextColor(Color.RED)

        leftDownRightLayout.orientation = LinearLayout.HORIZONTAL
        upLayout.orientation = LinearLayout.VERTICAL

        val layoutButton: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(210, 180)
        val layoutButtonUp: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(210, 180)
        val layoutButtonPause: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(210, 180)
        val layoutButtonInt: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(210, 180)
        val layoutSumaText: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(300, 200)
        val layoutPuntuacio: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(200, 200)

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
        val paramsLayoutScore: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )

        leftDownRightLayout.layoutParams = paramsButtons
        layoutButtonUp.leftMargin = 210
        upLayout.layoutParams = paramsButtons
        gameButtons.layoutParams = params
        auxLayout.layoutParams = paramsAux
        scoreLayout.layoutParams = paramsLayoutScore
        scoreLayout.orientation = LinearLayout.HORIZONTAL

        buttonLeft.layoutParams = layoutButton
        buttonDown.layoutParams = layoutButton
        buttonRight.layoutParams = layoutButton
        buttonUp.layoutParams = layoutButtonUp
        buttonPause.layoutParams = layoutButtonPause
        buttonInteraction.layoutParams = layoutButtonInt
        puntuacio.layoutParams = layoutPuntuacio
        sumaText.layoutParams = layoutSumaText

        layoutButtonPause.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        layoutButtonPause.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        layoutButtonInt.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        layoutButtonInt.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        paramsLayoutScore.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        paramsLayoutScore.addRule(RelativeLayout.ALIGN_LEFT)
        paramsAux.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        paramsAux.addRule(RelativeLayout.ALIGN_PARENT_LEFT)

        leftDownRightLayout.addView(buttonLeft)
        leftDownRightLayout.addView(buttonDown)
        leftDownRightLayout.addView(buttonRight)

        upLayout.addView(buttonUp)
        upLayout.addView(leftDownRightLayout)

        auxLayout.addView(upLayout)
        scoreLayout.addView(sumaText)
        scoreLayout.addView(puntuacio)

        gameButtons.addView(auxLayout)
        gameButtons.addView(buttonPause)
        gameButtons.addView(buttonInteraction)
        gameButtons.addView(scoreLayout)


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

    private fun getSfxMusica() {
        if (Guest.getGuest()) {
            if (AudioPlay.getSFX() != "" && AudioPlay.getMusica() != "") {
                sfx = AudioPlay.getSFX()
                musica = AudioPlay.getMusica()
            } else {
                sfx = getString(R.string.on)
                musica = getString(R.string.on)
            }
        } else {
            sfx = AudioPlay.getSFX()
            musica = AudioPlay.getMusica()
        }
        AudioPlay.setSFXValue(sfx)
        AudioPlay.setMusicaValue(musica)
    }

    val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    fun finestraPause(clickButton: Int, clickOptions: Int) {

        AudioPlay.pause()
        AudioPlay.playSfx(this, clickOptions)
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
            AudioPlay.playSfx(this, clickButton)
            AudioPlay.resumeMusic()
            //Recuperar estat del joc i tornar a la partida.
            buttonPause.setBackgroundResource(R.drawable.pause_button)
            AudioPlay.playSfx(this, R.raw.resume_button)
            gameView.resume()
            isPopupOn = false
            popupWindow.dismiss()
        }
        val opcionsJoc: Button = view.findViewById(R.id.opcions_button)
        opcionsJoc.setOnClickListener {
            AudioPlay.playSfx(this, clickOptions)
            popupWindow.dismiss()
            finestraOpcions(clickButton, clickOptions)
        }
        val menu_joc: Button = view.findViewById(R.id.menu_button)
        menu_joc.setOnClickListener {
            AudioPlay.playSfx(this, clickButton)
            AudioPlay.stopMusic()
            val intent = Intent(this, MainActivity::class.java)
            popupWindow.dismiss()
            finish()
            startActivity(intent)
        }
    }

    fun finestraOpcions(clickButton: Int, clickOptions: Int) {

        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater.inflate(R.layout.popup_option_menu, null)

        popupWindow = PopupWindow(view, 375.dp, 335.dp, false)
        popupWindow.showAtLocation(this.game, Gravity.CENTER, 0, 0)

        val buttonSfx: Button = view.findViewById(R.id.button_sfx)
        val buttonMusic: Button = view.findViewById(R.id.button_musica)
        val button_accept: Button = view.findViewById(R.id.button_accept)
        val button_deny: Button = view.findViewById(R.id.button_deny)

        buttonSfx.text = sfx
        buttonMusic.text = musica

        buttonSfx.setOnClickListener { onOff(buttonSfx) }
        buttonMusic.setOnClickListener { onOff(buttonMusic) }

        button_accept.setOnClickListener {
            sfx = buttonSfx.text.toString()
            musica = buttonMusic.text.toString()
            AudioPlay.setSFXValue(sfx)
            AudioPlay.setMusicaValue(musica)
            AudioPlay.updateMusica()
            if (sfx == AudioPlay.on)
                AudioPlay.playSfx(this, clickButton)
            if (!Guest.getGuest()) {
                search(Firebase.getAuth().uid.toString(), 2, "sfx")
                search(Firebase.getAuth().uid.toString(), 2, "music")
            }
            popupWindow.dismiss()
            finestraPause(clickButton, clickOptions)
        }
        button_deny.setOnClickListener {
            popupWindow.dismiss()
            finestraPause(clickButton, clickOptions)
        }
    }

    fun finestraVictoria(answer: Int) {

        if (level.solution != answer)
            return
        val restartButton = R.raw.restart_button
        val clickButton = R.raw.press_button

        AudioPlay.stopMusic()
        AudioPlay.playMusic(this, R.raw.you_win_music, false)
        val context = this
        runOnUiThread {
            gameView.pause()
            buttonPause.isEnabled = false
            buttonPause.visibility = View.INVISIBLE
            val aux = lvlSelected + 1
            if (!Guest.getGuest()) {
                Firebase.getReferenceUser().child(Firebase.getAuth().uid.toString()).child("Levels")
                    .child("level$aux").setValue(1)
            }
            LevelsArrays.setLevel(aux, "1")

            isPopupOn = true

            val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val view = inflater.inflate(R.layout.activity_you_win, null)

            popupWindow = PopupWindow(view, 375.dp, 335.dp, false)
            popupWindow.showAtLocation(game, Gravity.CENTER, 0, 0)

            val retry: Button = view.findViewById(R.id.retryButtonYW)
            val lvls: Button = view.findViewById(R.id.levelsbuttonYW)
            val next: Button = view.findViewById(R.id.nextButtonYW)

            retry.setOnClickListener {
                AudioPlay.playSfx(context, restartButton)

                popupWindow.dismiss()

                finish()
                AudioPlay.stopMusic()
                startActivity(intent)
            }

            lvls.setOnClickListener {
                AudioPlay.playSfx(context, clickButton)
                AudioPlay.stopMusic()
                AudioPlay.playMusic(context, R.raw.florian_bur_no_name, true)

                popupWindow.dismiss()
                val intent = Intent(context, LevelActivity::class.java)
                finish()
                startActivity(intent)
            }

            next.setOnClickListener {
                AudioPlay.playSfx(context, clickButton)
                popupWindow.dismiss()

                finish()
                AudioPlay.stopMusic()
                lvlSelected += 1
                if (lvlSelected < 10) {
                    intent.putExtra("level", lvlSelected.toString())
                    startActivity(intent)
                } else {
                    lvlSelected -= 1
                    intent.putExtra("level", lvlSelected.toString())
                    startActivity(intent)
                }
            }
        }
    }

    fun finestraDerrota() {

        val restartButton = R.raw.restart_button
        val clickButton = R.raw.press_button

        AudioPlay.stopMusic()
        AudioPlay.playMusic(this, R.raw.you_lose_music, false)
        val context = this

        runOnUiThread {
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
                AudioPlay.playSfx(this, restartButton)
                popupWindow.dismiss()

                finish()
                AudioPlay.stopMusic()
                startActivity(intent)
            }

            levels.setOnClickListener {
                AudioPlay.playSfx(this, clickButton)
                AudioPlay.stopMusic()
                AudioPlay.playMusic(context, R.raw.florian_bur_no_name, true)

                popupWindow.dismiss()
                val intent = Intent(context, LevelActivity::class.java)
                finish()
                startActivity(intent)
            }
        }
    }

    fun popup_enigma(buttonPause: Button) {
        runOnUiThread {
            isPopupOn = true
            buttonPause.isEnabled = false
            buttonPause.visibility = View.INVISIBLE

            val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val view = inflater.inflate(R.layout.popup_enigma, null)

            popupWindow = PopupWindow(view, 375.dp, 200.dp, true)
            popupWindow.showAtLocation(game, Gravity.CENTER, 0, 0)

            val enigma: ImageView = view.findViewById(R.id.enigmaImage)
            enigma.setImageResource(level.enigmaResource)

            popupWindow.setOnDismissListener {
                buttonPause.isEnabled = true
                buttonPause.visibility = View.VISIBLE
                isPopupOn = false
            }
        }
    }


    fun actionButtonColor(b: Boolean) {
        runOnUiThread {
            if (b) {
                buttons[4].setBackgroundColor(Color.YELLOW)
            } else {
                buttons[4].setBackgroundResource(android.R.drawable.btn_default)
            }
        }
    }

    private fun onOff(button: Button) {
        if (button.text.equals(getString(R.string.on))) {
            button.text = getString(R.string.off)
        } else {
            synchronized(AudioPlay) {
                button.text = getString(R.string.on)
            }
        }
    }

    fun setPuntuacio(i: Int) {
        suma += i
    }

    fun resetPuntuacio() {
        suma = 0
    }

    fun solucion(): Int {
        return level.solution
    }

    fun updateTextViewSuma() {
        runOnUiThread {
            if (suma < 10) puntuacio.text = "0$suma"
            else puntuacio.text = suma.toString()
        }
    }

    private fun search(key: String, action: Int, audio: String) {
        Firebase.getReferenceUser().addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (action == 1) {
                    Firebase.getReferenceUser().child(key).child("Audio").child(audio).addListenerForSingleValueEvent(
                        object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                if (audio == "sfx")
                                    sfx = dataSnapshot.value.toString()
                                else
                                    musica = dataSnapshot.value.toString()
                            }
                        }
                    )
                } else if (action == 2) {
                    if (audio == "sfx")
                        Firebase.getReferenceUser().child(key).child("Audio").child(audio).setValue(sfx)
                    else
                        Firebase.getReferenceUser().child(key).child("Audio").child(audio).setValue(musica)
                }
            }
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KEYCODE_HOME) {
            AudioPlay.stopMusic()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {} //Deshabilitar back button del mobil
}