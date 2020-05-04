package com.example.fichaexercicios.domain.auth.login.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.fichaexercicios.*
import com.example.fichaexercicios.domain.calculator.MainActivity
import com.example.fichaexercicios.domain.auth.register.activity.RegisterActivity
import com.example.fichaexercicios.data.entity.UserLogin
import com.example.fichaexercicios.domain.auth.login.viewModel.LoginViewModel

import kotlinx.android.synthetic.main.activity_login.*
import org.apache.commons.codec.digest.DigestUtils

const val EXTRA_LOGIN = "login"

class LoginActivity : AppCompatActivity() {
    private val TAG = LoginActivity::class.java.simpleName
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)



        button_login.setOnClickListener{

            val name = campo_login_name.text.toString()
            val pass = DigestUtils.sha256Hex(campo_login_password.text.toString())
            var email = viewModel.validaLogin(name, pass)

            if( email != ""){
                val userLogin = UserLogin(name, email)

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
            startActivity(intent)
            finish()
        }

    }



}
