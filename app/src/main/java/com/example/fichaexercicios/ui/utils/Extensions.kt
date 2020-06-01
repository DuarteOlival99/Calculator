package com.example.fichaexercicios.ui.utils

import android.location.Location
import com.google.android.gms.maps.model.LatLng

class Extensions {

    fun LocationToLatLng(location: Location) : LatLng {

        return LatLng(location.latitude, location.longitude)

    }

}