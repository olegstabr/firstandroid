package com.example.olegs.firstapp.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import com.example.olegs.firstapp.R

/**
 * Created by superadmin on 29.07.17.
 */

class NoteEditActivity : AppCompatActivity() {
    lateinit var titleText: EditText
    lateinit var textText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_edit)
        titleText = findViewById(R.id.edit_text_title) as EditText
        textText = findViewById(R.id.edit_text_note) as EditText
        titleText.setText(intent.getStringExtra("title"), TextView.BufferType.EDITABLE)
        val textBytes = intent.getByteArrayExtra("text")
        val text = String(textBytes)
        textText.setText(text, TextView.BufferType.EDITABLE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_note, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id) {
            R.id.action_save_note -> {
            }
        }

        return super.onOptionsItemSelected(item)
    }
}