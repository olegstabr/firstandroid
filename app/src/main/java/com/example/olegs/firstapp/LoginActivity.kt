package com.example.olegs.firstapp

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import com.example.olegs.firstapp.Auth.BasicAuthRestTemplate
import com.example.olegs.firstapp.Rest.User
import org.springframework.http.HttpStatus
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter


/**
 * Created by superadmin on 21.07.17.
 */

class LoginActivity : AppCompatActivity() {
    var loginText: EditText? = null
    var passwordText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initUI()
    }

    fun initUI() {
        val signupLink = findViewById(R.id.link_signup)
        signupLink.setOnClickListener {
            val intent = Intent(applicationContext, SignupActivity::class.java)
            startActivity(intent)
        }

        val loginButton = findViewById(R.id.btn_login)
        loginButton.setOnClickListener {
            val sendLoginData = SendLoginData()
            sendLoginData.execute()
        }
    }

    inner class SendLoginData : AsyncTask<Void, Void, HttpStatus>() {
        override fun doInBackground(vararg params: Void?): HttpStatus? {
            try {
                val url = "http://192.168.0.104:8080/login"
                loginText = findViewById(R.id.input_login) as EditText
                passwordText = findViewById(R.id.input_password) as EditText
                val login = loginText?.text.toString()
                val password = passwordText?.text.toString()
                val restTempalte = BasicAuthRestTemplate("bill", "abc123")

                restTempalte.messageConverters.add(MappingJackson2HttpMessageConverter())
                val response = restTempalte.postForObject(url, User(login, password), HttpStatus::class.java)
                return response
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(response: HttpStatus?) {
            if (response != HttpStatus.OK) {
                return
            }
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext, "Вы успешно авторизовались", Toast.LENGTH_SHORT).show()
        }
    }
}
