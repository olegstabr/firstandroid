package com.example.olegs.firstapp

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.olegs.firstapp.Auth.BasicAuthRestTemplate
import com.example.olegs.firstapp.Rest.User
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

/**
 * Created by superadmin on 11.07.17.
 */

class SignupActivity : AppCompatActivity() {
    open var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        onCreateButtonClick()
    }

    fun onCreateButtonClick() {
        val createButton = findViewById(R.id.btn_signup)
        createButton.setOnClickListener(View.OnClickListener {
            val loginText = findViewById(R.id.input_name) as EditText
            val passwordText = findViewById(R.id.input_password) as EditText
            val login = loginText.text
            val password = passwordText.text
            val httpRequestTask = HttpRequestTask()
            httpRequestTask.count = i++;
            httpRequestTask.execute()
        })
    }

    inner class  HttpRequestTask : AsyncTask<Void, Void, User>() {
        var count = 0;
        override fun doInBackground(vararg params: Void?): User? {
            try {
                val url = "http://192.168.0.104:8080/user/"
                val restTempalte = BasicAuthRestTemplate("bill", "abc123")
                restTempalte.messageConverters.add(MappingJackson2HttpMessageConverter())
                val user = restTempalte.postForObject(url, User(count, count.toString(), "CXCXCXC"), User::class.java)
                return user
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(user: User?) {
            Toast.makeText(applicationContext, "OKOKOK", Toast.LENGTH_SHORT).show()
        }
    }
}
