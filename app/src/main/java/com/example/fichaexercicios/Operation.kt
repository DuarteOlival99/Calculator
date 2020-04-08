package com.example.fichaexercicios

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Operation(val exprexion: String, val result: String) : Parcelable{
    override fun toString(): String {
        return "$exprexion = $result"
    }


}