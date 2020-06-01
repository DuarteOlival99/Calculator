package com.example.fichaexercicios.domain.calculator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.fichaexercicios.R
import com.example.fichaexercicios.data.entity.UserLogin
import com.example.fichaexercicios.data.sensors.location.FusedLocation
import com.example.fichaexercicios.data.sensors.location.OnLocationChangedListener
import com.example.fichaexercicios.domain.auth.login.activity.LoginActivity
import com.example.fichaexercicios.ui.utils.NavigationManager
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

const val EXTRA_LOGIN = "login"

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener{
    private val TAG = MainActivity::class.java.simpleName
    private var token = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "o metodo onCreate foi invocado")
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupDrawerMenu()
        if(!screenRotated(savedInstanceState)){
            NavigationManager.goToCalculatorFragment(
                supportFragmentManager
            )
        }

        val user = intent.getParcelableExtra<UserLogin>(EXTRA_LOGIN)
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

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.apply {
            token = getString("token", "").toString()
            Log.i("token", token)
        }

        if(token != ""){
            Log.i("entrou", "entrou")
            NavigationManager.goToCalculatorFragment(
                supportFragmentManager
            )
        }else{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun screenRotated(savedInstanceState: Bundle?) : Boolean {
        return savedInstanceState != null
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_calculator -> {
                NavigationManager.goToCalculatorFragment(
                    supportFragmentManager
                )
            }
            R.id.nav_history -> {
                NavigationManager.goToHistoryFragment(
                    supportFragmentManager
                )
            }
            R.id.nav_logout -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_map -> {
                NavigationManager.goToMapFragment(
                    supportFragmentManager
                )
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }


    private fun setupDrawerMenu() {
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
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

