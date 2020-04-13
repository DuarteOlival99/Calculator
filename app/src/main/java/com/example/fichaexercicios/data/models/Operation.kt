package com.example.fichaexercicios.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

class Operation(val exprexion: String, val result: Double){

    var uuid: String = UUID.randomUUID().toString()

    override fun toString(): String {
        return "$exprexion = $result"
    }


}