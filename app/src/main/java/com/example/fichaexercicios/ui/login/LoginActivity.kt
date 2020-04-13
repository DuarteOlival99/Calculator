package com.example.fichaexercicios

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast

import com.example.fichaexercicios.R
import kotlinx.android.synthetic.main.activity_login.*
import org.apache.commons.codec.digest.DigestUtils

const val EXTRA_LOGIN = "login"
private val hash: String = DigestUtils.sha256Hex("teste")
private val useradmin : User = User("teste", "teste@gmail.com", hash)
private var userList = mutableListOf<User>(useradmin)


class LoginActivity : AppCompatActivity() {
    private val TAG = LoginActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val user = intent.getParcelableExtra<User>(EXTRA_REGISTER)
        Log.i(TAG, useradmin.pass)

        if (user != null) {
            userList.add(user)
        }
        Log.i(TAG, userList.toString())


        button_login.setOnClickListener{

            val name = campo_login_name.text.toString()
            var email = ""
            val pass = campo_login_password.text.toString()

            var aux = false
            val passTestar: String = DigestUtils.sha256Hex(pass)
            for (userTeste in userList){
                if (userTeste.name == name && userTeste.pass == passTestar){
                    email = userTeste.email
                    aux = true
                    break
                }
            }

            if(aux){
                val userLogin : User = User(name, email, pass)
                val intent = Intent(this, MainActivity::class.java)
                intent.apply { putExtra(EXTRA_LOGIN, userLogin) }
                Log.i(TAG, userLogin.toString())
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Password ou Username Invalido", Toast.LENGTH_SHORT).show()
            }

        }

        button_register.setOnClickListener{

            val intent = Intent(this, RegisterActivity::class.java)
            intent.apply { putParcelableArrayListExtra(EXTRA_LOGIN, ArrayList(userList)) }
            startActivity(intent)
            finish()

        }

    }
}
