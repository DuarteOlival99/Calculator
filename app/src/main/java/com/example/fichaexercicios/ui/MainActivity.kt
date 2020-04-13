package com.example.fichaexercicios

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_header.*

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener{
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "o metodo onCreate foi invocado")
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupDrawerMenu()
        if(!screenRotated(savedInstanceState)){
            NavigationManager.goToCalculatorFragment(supportFragmentManager)
        }
        val user = intent.getParcelableExtra<User>(EXTRA_LOGIN)
        if(user != null){

            Log.i(TAG, user.toString())
            val name = user.name
            val email = user.email
            Log.i(TAG, email)
            Log.i(TAG, name)
            val navigationView : NavigationView  = findViewById(R.id.nav_drawer)
            val drawerView : View  = navigationView.getHeaderView(0)
            val navUsername : TextView = drawerView.findViewById(R.id.drawer_title)
            val navUserEmail : TextView = drawerView.findViewById(R.id.drawer_subtitle)

            navUsername.text = name
            navUserEmail.text = email

        }

    }

    private fun screenRotated(savedInstanceState: Bundle?) : Boolean {
        return savedInstanceState != null
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_calculator -> {
                NavigationManager.goToCalculatorFragment(supportFragmentManager)
            }
            R.id.nav_history -> {
                NavigationManager.goToHistoryFragment(supportFragmentManager)
            }
            R.id.nav_logout -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }


    private fun setupDrawerMenu() {
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar,
            R.string.drawer_open, R.string.drawer_close)
        nav_drawer.setNavigationItemSelectedListener(this)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

    }

    override fun onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        }
        if (supportFragmentManager.backStackEntryCount == 1){
            finish()
        }
            super.onBackPressed()
    }

}

