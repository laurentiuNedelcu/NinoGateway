package com.example.ninosproject.Activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.example.ninosproject.Logic.GameView

class PlayActivity : AppCompatActivity() {

    private lateinit var gameView: GameView
    private lateinit var game: FrameLayout //Contindra la vista del gameView y de los botones
    private lateinit var gameButtons: RelativeLayout
    private lateinit var auxLayout: RelativeLayout
    private lateinit var leftDownRightLayout: LinearLayout
    private lateinit var upLayout: LinearLayout

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gameView = GameView(this)
        game = FrameLayout(this)
        gameButtons = RelativeLayout(this)
        auxLayout = RelativeLayout(this)
        leftDownRightLayout = LinearLayout(this)
        upLayout = LinearLayout(this)


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
        buttonPause.text = "||"
        buttonPause.id = 555666

        val buttonInteraction = Button(this)
        buttonInteraction.text = "A"
        buttonInteraction.id = 666777

        leftDownRightLayout.orientation = LinearLayout.HORIZONTAL
        upLayout.orientation = LinearLayout.VERTICAL

        val layoutButton: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(210, 180)
        val layoutButtonUp: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(210, 180)
        val layoutButtonPause: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(210, 180)
        val layoutButtonInt: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(210, 180)

        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT)
        val paramsButtons: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        val paramsAux: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT)

        leftDownRightLayout.layoutParams = paramsButtons
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

        val buttons : ArrayList<Button> = ArrayList()
        buttons.add(buttonLeft)
        buttons.add(buttonDown)
        buttons.add(buttonRight)
        buttons.add(buttonUp)

        gameView.addButtons(buttons)
        setContentView(game)

        gameView.startGame()
    }
}
