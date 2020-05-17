package com.example.fichaexercicios.domain.auth.login.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.fichaexercicios.*
import com.example.fichaexercicios.domain.calculator.MainActivity
import com.example.fichaexercicios.domain.auth.register.activity.RegisterActivity
import com.example.fichaexercicios.data.entity.UserLogin
import com.example.fichaexercicios.domain.auth.login.viewModel.LoginViewModel
import com.example.fichaexercicios.ui.listeners.OnLoginTry
import com.example.fichaexercicios.ui.utils.NavigationManager

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.apache.commons.codec.digest.DigestUtils

const val EXTRA_LOGIN = "login"

class LoginActivity : AppCompatActivity(), OnLoginTry {
    private val TAG = LoginActivity::class.java.simpleName
    private lateinit var viewModel: LoginViewModel
    private var token = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        Log.i("Login", viewModel.getUsers().toString())




        button_login.setOnClickListener{

            val name = campo_login_name.text.toString()
            val pass = DigestUtils.sha256Hex(campo_login_password.text.toString())
            val passToken = campo_login_password.text.toString()
            //var email = viewModel.validaLogin(name, pass)
            //Log.i("login", email)

            viewModel.onClickLogin(name, passToken)
           val a = viewModel.getToken()
            Log.i("teste", a.toString())
//
////            viewModel.valida(name, pass)
////            Log.i("token", viewModel.getToken().toString())
//
//            var ok = false
//
//            CoroutineScope(Dispatchers.IO).launch {
//                viewModel.valida(name, passToken)
//                Log.i("token2", viewModel.getToken().toString())
//                ok = true
//            }

//            if( email != ""){
//                val userLogin = UserLogin(name, email)
//
//                val intent = Intent(this, MainActivity::class.java)
//                intent.apply { putExtra(EXTRA_LOGIN, userLogin) }
//                Log.i(TAG, userLogin.toString())
//                startActivity(intent)
//                finish()
//            }else{
//                Toast.makeText(this, "Password ou Username Invalido", Toast.LENGTH_SHORT).show()
//            }

        }

        button_register.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onStart() {
        viewModel.registerListener(this)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.apply {
            val tokenn = getString("token", "").toString()
            Log.i("token", tokenn)
        }

        super.onStart()
    }

    override fun onDestroy() {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        viewModel.unregisterListener()
        super.onDestroy()
    }

    override fun onLoginTry(boolean: Boolean) {
        Handler(Looper.getMainLooper()).post(Runnable { if (boolean){
            Toast.makeText(this, "Bem vindo!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else {
            campo_login_name.error = "Password/Username Invalido"
            campo_login_password.error = "Password/Username Invalido"
            Toast.makeText(this, "Password/Username Invalido", Toast.LENGTH_SHORT).show()
        }
        })
    }



}
