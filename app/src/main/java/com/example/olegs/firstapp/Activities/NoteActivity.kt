package com.example.olegs.firstapp.Activities

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.olegs.firstapp.Auth.BasicAuthRestTemplate
import com.example.olegs.firstapp.R
import com.example.olegs.firstapp.Rest.Note
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import java.sql.Date

/**
 * Created by superadmin on 25.07.17.
 */

class NoteActivity : AppCompatActivity() {
    lateinit var titleText: EditText
    lateinit var textText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        titleText = findViewById(R.id.edit_text_title) as EditText
        textText = findViewById(R.id.edit_text_note) as EditText
        titleText.setText(intent.getStringExtra("title"), TextView.BufferType.EDITABLE)
        textText.setText(intent.getStringExtra("text"), TextView.BufferType.EDITABLE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_note, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id) {
            R.id.action_save_note -> {
                val saveNote = SaveNote()
                saveNote.execute()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    inner class SaveNote : AsyncTask<Void, Void, ResponseEntity<*>>() {
        lateinit var progressDialog: ProgressDialog

        override fun doInBackground(vararg params: Void?): ResponseEntity<*>? {
            try {
                val url = "http://192.168.0.104:8080/note"
                val title = titleText?.text.toString()
                val text = textText?.text.toString()
                val restTempalte = BasicAuthRestTemplate.instance

                restTempalte.messageConverters.add(MappingJackson2HttpMessageConverter())
                var note = Note(title, text, Date(System.currentTimeMillis()))
                note.id = intent.getIntExtra("id", 0)
                val response = restTempalte.postForEntity(url, note, ResponseEntity::class.java)
                return response
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(response: ResponseEntity<*>?) {
            android.os.Handler().postDelayed({
                progressDialog.dismiss()
            }, 500)

            if (response?.statusCode == HttpStatus.CONFLICT) {
                Toast.makeText(applicationContext, "Что-то пошло не так, заметка не сохранилась...", Toast.LENGTH_SHORT).show()
                return
            }
            Toast.makeText(applicationContext, "Заметка успешно сохранена", Toast.LENGTH_SHORT).show()
        }

        override fun onPreExecute() {
            progressDialog = ProgressDialog(this@NoteActivity)
            progressDialog.isIndeterminate = true
            progressDialog.setMessage("Сохранение...")
            progressDialog.show()
        }
    }
}