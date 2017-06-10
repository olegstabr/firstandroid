package com.example.olegs.firstapp

import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var helloTextView :TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        helloTextView = findViewById(R.id.textView) as? TextView
//        val button = findViewById(R.id.button)
//        var i = 0
//        button.setOnClickListener(onClickListener)
//        helloTextView?.setOnClickListener(onClickListener)
    }

//    open val onClickListener : OnClickListener = OnClickListener() {
//        v -> val id = v.id
//        Log.d("MyLogs", "Click Event")
//        when (id) {
//            R.id.textView -> {
//                helloTextView?.text = "TextView Clicked"
//                Toast.makeText(this, "Вы нажали на TextView", Toast.LENGTH_SHORT).show()
//            }
//            R.id.button -> {
//                helloTextView?.text = "Button Clicked"
//                Toast.makeText(this, "Вы нажали на Button", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
}
