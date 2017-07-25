package com.example.olegs.firstapp.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.example.olegs.firstapp.R

/**
 * Created by superadmin on 25.07.17.
 */

class NoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_note, menu)
        return true;
    }
}