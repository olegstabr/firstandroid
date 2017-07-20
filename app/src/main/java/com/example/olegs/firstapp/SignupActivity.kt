package com.example.olegs.firstapp

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.olegs.firstapp.Auth.BasicAuthRestTemplate
import com.example.olegs.firstapp.Rest.User
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

/**
 * Created by superadmin on 11.07.17.
 */

class SignupActivity : AppCompatActivity() {
    var loginText: EditText? = null
    var passwordText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        onCreateButtonClick()
    }

    fun onCreateButtonClick() {
        val createButton = findViewById(R.id.btn_signup)
        createButton.setOnClickListener({
            val sendLoginData = SendLoginData()
            sendLoginData.execute()
        })
    }

    inner class SendLoginData : AsyncTask<Void, Void, User>() {
        override fun doInBackground(vararg params: Void?): User? {
            try {
                val url = "http://192.168.0.104:8080/user/"
                loginText = findViewById(R.id.input_name) as EditText
                passwordText = findViewById(R.id.input_password) as EditText
                val login = loginText?.text.toString()
                val password = passwordText?.text.toString()
                val restTempalte = BasicAuthRestTemplate("bill", "abc123")

                restTempalte.messageConverters.add(MappingJackson2HttpMessageConverter())
                val user = restTempalte.postForObject(url, User(login, password), User::class.java)
                return user
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(user: User?) {
            loginText?.setText("", TextView.BufferType.EDITABLE)
            passwordText?.setText("", TextView.BufferType.EDITABLE)
            Toast.makeText(applicationContext, "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show()
        }
    }
}
