package com.example.olegs.firstapp

import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    open val NOTIFY_ID = 101

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when (id) {
            R.id.action_item1 -> Toast.makeText(applicationContext, "Вы создали уведомление", Toast.LENGTH_LONG).show()
            R.id.action_item2 -> Toast.makeText(applicationContext, "Вы выбрали пункт 2", Toast.LENGTH_LONG).show()
            R.id.action_item3 -> Toast.makeText(applicationContext, "Вы выбрали пункт 3", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }

    open val onSnackbarClickListener : View.OnClickListener = OnClickListener {
        Toast.makeText(applicationContext, "Good Job!", Toast.LENGTH_SHORT).show()
    }
}
