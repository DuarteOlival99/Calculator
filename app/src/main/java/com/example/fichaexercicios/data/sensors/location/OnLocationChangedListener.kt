package com.example.fichaexercicios.data.sensors.location

import com.google.android.gms.location.LocationResult

interface OnLocationChangedListener {

    fun onLocationChanged(locastionResult: LocationResult)

}