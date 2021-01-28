package com.example.tictactoe
import androidx.lifecycle.ViewModel

enum class TttSquareState {
    EMPTY, ECKS, OHH
}

enum class TttResult {
    UNKNOWN, ECKS, OHH, TIE
}

class TttGame : ViewModel() {
    var turn : Boolean = false
    var boardState : Array<TttSquareState> = Array(9) {TttSquareState.EMPTY}
    var gameState : TttResult = TttResult.UNKNOWN

    init {
        println("New game initialized!")
    }

    private fun winCheckCase (indA : Int, indB : Int, indC : Int) : Boolean =
        boardState[indB] != TttSquareState.EMPTY
                && boardState[indA] == boardState[indB]
                && boardState[indA] == boardState[indC]

    private fun setWinner(center : Int) {
        if(boardState[center] == TttSquareState.ECKS) {
            gameState = TttResult.ECKS
        } else {
            gameState = TttResult.OHH
        }
    }

    fun updateGame() {
        //Passsing through center
        if( winCheckCase(0, 4, 8) ||
            winCheckCase(6, 4, 2) ||
            winCheckCase(1, 4, 7) ||
            winCheckCase(3, 4, 5)) {
            setWinner(4)
        } else if(winCheckCase(0, 1, 2) || //Passing through upper left corner
                  winCheckCase(0, 3, 6)) {
            setWinner(0)
        } else if(winCheckCase(6, 7, 8) || //Passing through bottom right corner
                  winCheckCase(2, 5, 8)) {
            setWinner(8)
        } else if(boardState.any {it == TttSquareState.EMPTY}) {
            gameState = TttResult.UNKNOWN
        } else {
            gameState = TttResult.TIE
        }
    }

    fun resetGame() {
        turn = false
        boardState = Array(9) {TttSquareState.EMPTY}
        gameState = TttResult.UNKNOWN
    }
}