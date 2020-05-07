package com.example.fichaexercicios.domain.auth.register.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.fichaexercicios.R
import com.example.fichaexercicios.data.entity.User
import com.example.fichaexercicios.domain.auth.login.activity.LoginActivity
import com.example.fichaexercicios.domain.auth.register.viewModel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*
import org.apache.commons.codec.digest.DigestUtils

const val EXTRA_REGISTER = "register"

class RegisterActivity : AppCompatActivity() {
    private val TAG = RegisterActivity::class.java.simpleName
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)

        button_cancel.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        button_register_page.setOnClickListener {

            if (button_password.text.toString().isNotEmpty() && button_confirm_password.text.toString().isNotEmpty()){

                val name = campo_name.text.toString()
                val pass = DigestUtils.sha256Hex(button_password.text.toString())
                val password = button_password.text.toString()
                val passConfirm = DigestUtils.sha256Hex(button_confirm_password.text.toString())
                val email = campo_email.text.toString()

                if(validaCampos(name, email, pass, passConfirm)){
                    val user: User = User(name, email, pass)

                    viewModel.onClickRegister(name, email, password)

                    viewModel.newUser(user)

                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }else{
                Toast.makeText(this, "Palavra Passe não introduzida", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun validaCampos(name: String, email: String, pass: String, passConfirm: String) : Boolean {

        if (viewModel.validaNome(name)){
            if (viewModel.validaEmail(email)){
                if(viewModel.validaPass(pass, passConfirm)){
                    if (viewModel.validaUserList(name)){
                        return true
                    }else{
                        Toast.makeText(
                            this,
                            "Nome ja existente",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }else{
                    Toast.makeText(
                        this,"As password não correspondem, volte a tentar",
                        Toast.LENGTH_SHORT).show()
                    button_password.text.clear()
                    button_confirm_password.text.clear()
                }
            }else{
                Toast.makeText(this, "Email Invalido", Toast.LENGTH_SHORT).show()
                campo_email.text.clear()
            }
        }else{
            Toast.makeText(this, "Introduza um username", Toast.LENGTH_SHORT).show()
        }
        return false
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}