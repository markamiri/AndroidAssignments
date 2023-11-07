package com.example.androidassignments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val ACTIVITY_NAME = "MainActivity" // Define ACTIVITY_NAME as a property


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startChatButton = findViewById<Button>(R.id.startChatButton)
        startChatButton.setOnClickListener {
            Log.i(ACTIVITY_NAME, "User clicked Start Chat")
            val intent = Intent(this, ChatWindow::class.java)
            startActivity(intent)  // Start the ChatWindow activity

        }

        // Add code to start the TestToolbar activity
        val testToolbarButton = findViewById<Button>(R.id.startTestToolbarButton)
        testToolbarButton.setOnClickListener {
            val testToolbarIntent = Intent(this, TestToolbar::class.java)
            startActivity(testToolbarIntent)  // Start the TestToolbar activity
        }

    }
    override fun onStart() {
        super.onStart()

        // Custom code for onStart
        Log.i("MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()

        // Custom code for onResume
        Log.i("MainActivity", "onResume")
    }

    override fun onPause() {
        // Custom code for onPause
        Log.i("MainActivity", "onPause")

        super.onPause()
    }

    override fun onStop() {
        // Custom code for onStop
        Log.i("MainActivity", "onStop")

        super.onStop()
    }

    override fun onDestroy() {
        // Custom code for onDestroy
        Log.i("MainActivity", "onDestroy")

        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // Custom code for onSaveInstanceState
        Log.i("MainActivity", "onSaveInstanceState")

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // Custom code for onRestoreInstanceState
        Log.i("MainActivity", "onRestoreInstanceState")
    }


}

