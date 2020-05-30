package com.example.fichaexercicios.ui.utils

import android.app.Application
import com.example.fichaexercicios.data.sensors.location.FusedLocation

class CalculatorApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FusedLocation.start(this)
    }

}