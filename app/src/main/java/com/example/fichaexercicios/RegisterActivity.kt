package com.example.fichaexercicios

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.apache.commons.codec.digest.DigestUtils

const val EXTRA_REGISTER ="register"

class RegisterActivity : AppCompatActivity() {

    private val TAG = RegisterActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val userList = intent.getParcelableArrayListExtra<Parcelable>(EXTRA_LOGIN)

        if(userList != null){
            for (user in userList){
                Log.i(TAG, user.toString())
            }
        }else{
            Log.i(TAG, "ta vazia")
        }


        button_cancel.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        button_register_page.setOnClickListener{

            val name = campo_name.text.toString()
            val pass = DigestUtils.sha256Hex(button_password.text.toString())
            val passConfirm = DigestUtils.sha256Hex(button_confirm_password.text.toString())
            val email = campo_email.text.toString()

            //val hash: String = DigestUtils.sha256Hex("password")

            if(pass == passConfirm){ //valida se as password correspondem

                if(isEmailValid(email)){ //valida se o email e valido

                    if(!campo_name.text.isEmpty()){ // valida se o username nao esta vazio

                        var aux = false
//                        if(userList != null){
//                            for (user in userList){
//                                if (user.javaClass.name == name){
//                                    Log.i(TAG, user.javaClass.name + "teste")
//                                    aux = true
//                                    break
//                                }
//                            }
//                        }else{
//                            Log.i(TAG, "ta vazia")
//                        }

                        if(!aux){

                            val user : User = User(name, email, pass)
                            val intent = Intent(this, LoginActivity::class.java)
                            intent.apply { putExtra(EXTRA_REGISTER, user) }
                            startActivity(intent)
                            finish()

                        }else{
                            Toast.makeText(this, "Username ja existe", Toast.LENGTH_SHORT).show()
                        }


                    }else{
                        Toast.makeText(this, "Introduza um username", Toast.LENGTH_SHORT).show()
                    }

                }else{
                    Toast.makeText(this, "Email Invalido", Toast.LENGTH_SHORT).show()
                    campo_email.text.clear()
                }

            }else{

                Toast.makeText(this, "As password não correspondem, volte a tentar", Toast.LENGTH_SHORT).show()
                button_password.text.clear()
                button_confirm_password.text.clear()
            }

        }
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}
