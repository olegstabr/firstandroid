package com.example.olegs.firstapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val helloTextView = findViewById(R.id.textView)
        if (helloTextView is TextView) {
            val button = findViewById(R.id.button)
            var i = 0
            button.setOnClickListener(View.OnClickListener {
                helloTextView.text = i++.toString()
            })
        }
    }
}
