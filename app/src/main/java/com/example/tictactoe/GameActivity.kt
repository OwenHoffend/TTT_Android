package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.viewModels

class GameActivity : AppCompatActivity() {
    private val gameInst : TttGame by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) { //savedInstanceState contains information about the activity from previous times it was running
        super.onCreate(savedInstanceState)
        println("Game Activity Created!")
        setContentView(R.layout.activity_game) //R contains the app's resources
        updateGameUI()
    }

    private fun updateGameUI() {
        for(tileID in 0..8) {
            (findViewById<TableLayout>(R.id.MainBoard)
                .getChildAt(tileID / 3) as TableRow)
                .getChildAt(tileID % 3)
                .background = when(gameInst.boardState[tileID]) {
                TttSquareState.ECKS  -> getDrawable(R.drawable.ic_ecks)
                TttSquareState.OHH   -> getDrawable(R.drawable.ic_ohh)
                TttSquareState.EMPTY -> getDrawable(R.color.colorNothing)
            }
        }
        val resetButton = findViewById<Button>(R.id.resetbutton)
        val winTextView = findViewById<TextView>(R.id.wintext)
        if(gameInst.gameState != TttResult.UNKNOWN) {
            println("The game has ended. The winner is ${gameInst.gameState}")
            resetButton.visibility = View.VISIBLE
            winTextView.text = "The winner is ${gameInst.gameState}!"
        } else {
            resetButton .visibility = View.INVISIBLE
            if(gameInst.turn) {
                winTextView.text = "OHH Turn!"
            } else {
                winTextView.text = "ECKS Turn!"
            }
        }
    }

    fun processResetButton(view: View) {
        gameInst.resetGame()
        updateGameUI()
    }

    fun processGridButton(view: View) {
        val buttonPressed : Int = (view.tag as String).toInt()
        println("Button pressed: $buttonPressed")
        if (gameInst.gameState == TttResult.UNKNOWN
            && gameInst.boardState[buttonPressed] == TttSquareState.EMPTY){
            if (gameInst.turn) {
                gameInst.boardState[buttonPressed] = TttSquareState.OHH
            } else {
                gameInst.boardState[buttonPressed] = TttSquareState.ECKS
            }
            gameInst.turn = !gameInst.turn

            gameInst.updateGame()
            updateGameUI()
        }
    }

    fun processExitGameButton(view : View) {
        finish()
    }

    override fun onStart() {
        super.onStart()
        println("Game Activity Started!")
    }

    override fun onResume() {
        super.onResume()
        println("Game Activity Resumed!")
    }

    override fun onPause() {
        super.onPause()
        println("Game Activity Paused!")
    }

    override fun onStop() {
        super.onStop()
        println("Game Activity Stopped!")
    }

    override fun onRestart() {
        super.onRestart()
        println("Game Activity Restarted!")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("Game Activity Destroyed!")
    }
}