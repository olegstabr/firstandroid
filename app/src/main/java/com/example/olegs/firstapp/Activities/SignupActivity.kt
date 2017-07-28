package com.example.olegs.firstapp.Activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.olegs.firstapp.Auth.BasicAuthRestTemplate
import com.example.olegs.firstapp.R
import com.example.olegs.firstapp.Rest.User
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

/**
 * Created by superadmin on 11.07.17.
 */

class SignupActivity : AppCompatActivity() {
    var loginText: EditText? = null
    var emailText: EditText? = null
    var passwordText: EditText? = null
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        onCreateButtonClick()
        onLoginLinkClick()
    }

    fun validate(): Boolean {
        var valid = true
        loginText = findViewById(R.id.input_login) as EditText
        emailText = findViewById(R.id.input_email) as EditText
        passwordText = findViewById(R.id.input_password) as EditText
        val login = loginText?.text.toString()
        val password = passwordText?.text.toString()
        val email = emailText?.text.toString()

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText?.error = "Введите корректный адрес"
            valid = false
        } else {
            emailText?.error = null
        }

        if (password.isEmpty() || password.length < 4) {
            passwordText?.error = "Пароль должен содержать больше 4 символов"
            valid = false
        } else {
            passwordText?.error = null
        }

        if (login.isEmpty() || login.length < 3) {
            loginText?.error = "Логин должен содержать больше 3 символов"
            valid = false
        } else {
            loginText?.error = null
        }

        return valid
    }

    fun onCreateButtonClick() {
        val createButton = findViewById(R.id.btn_signup)
        createButton.setOnClickListener({
            if (validate()) {
                progressDialog = ProgressDialog(this)
                progressDialog.isIndeterminate = true
                progressDialog.setMessage("Регистрация...")
                progressDialog.show()

                val sendLoginData = SendLoginData()
                sendLoginData.execute()

                android.os.Handler().postDelayed({
                    progressDialog.dismiss()
                }, 3000)
            }
        })
    }

    fun onLoginLinkClick() {
        val loginLink = findViewById(R.id.link_login)
        loginLink.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    inner class SendLoginData : AsyncTask<Void, Void, User>() {
        override fun doInBackground(vararg params: Void?): User? {
            try {
                val url = "http://192.168.0.104:8080/user/"
                loginText = findViewById(R.id.input_login) as EditText
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
            android.os.Handler().postDelayed({
                progressDialog.dismiss()
            }, 3000)
            loginText?.setText("", TextView.BufferType.EDITABLE)
            passwordText?.setText("", TextView.BufferType.EDITABLE)
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext, "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show()
        }
    }
}
