package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("Main Activity Started!")
        setContentView(R.layout.activity_main) //R contains the app's resources
    }

    fun processStartGameButton(view : View){
        val startGameIntent = Intent(this, GameActivity::class.java)
        startActivity(startGameIntent)
    }

    fun processStart9x9GameButton(view : View) {
        println("9x9 game not implemented yet")
    }

    override fun onStart() {
        super.onStart()
        println("Main Activity Started!")
    }

    override fun onResume() {
        super.onResume()
        println("Main Activity Resumed!")
    }

    override fun onPause() {
        super.onPause()
        println("Main Activity Paused!")
    }

    override fun onStop() {
        super.onStop()
        println("Main Activity Stopped!")
    }

    override fun onRestart() {
        super.onRestart()
        println("Main Activity Restarted!")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("Main Activity Destroyed!")
    }
}