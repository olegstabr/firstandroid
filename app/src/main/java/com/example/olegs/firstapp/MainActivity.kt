package com.example.olegs.firstapp

import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionButton = findViewById(R.id.main_floatbutton)
        actionButton?.setOnClickListener(OnClickListener {
            v -> v
            val snackbar = Snackbar.make(v, "Own action", Snackbar.LENGTH_SHORT).setAction("OK", onSnackbarClickListener)
            snackbar.show()

        })
    }

    open val onSnackbarClickListener : View.OnClickListener = OnClickListener {
        Toast.makeText(applicationContext, "Good Job!", Toast.LENGTH_SHORT).show()
    }
}
