package com.example.olegs.firstapp.Activities

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.olegs.firstapp.Auth.BasicAuthRestTemplate
import com.example.olegs.firstapp.R
import com.example.olegs.firstapp.Rest.Note
import com.example.olegs.firstapp.Rest.NotesList
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

/**
 * Created by superadmin on 24.07.17.
 */

class NotesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)
        val saveNote = SaveNote()
        saveNote.execute()
    }

    override fun onResume() {
        super.onResume()
        val saveNote = SaveNote()
        saveNote.execute()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_note_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id) {
            R.id.action_add_note -> {
                val intent = Intent(applicationContext, NoteActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class SaveNote : AsyncTask<Void, Void, NotesList>() {
        override fun doInBackground(vararg params: Void?): NotesList? {
            try {
                val url = "http://192.168.0.104:8080/user/1/note"
                val restTempalte = BasicAuthRestTemplate("bill", "abc123")

                restTempalte.messageConverters.add(MappingJackson2HttpMessageConverter())
                val response = restTempalte.getForObject(url, NotesList::class.java)
                return response
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(response: NotesList?) {
            val listView = findViewById(R.id.list_view) as ListView
            val noteNames= ArrayList<String>()
            response?.userList?.forEach { noteNames.add(it.title) }
            val adapter = ArrayAdapter<String>(this@NotesListActivity, android.R.layout.simple_list_item_1, noteNames)
            listView?.adapter = adapter
        }
    }
}