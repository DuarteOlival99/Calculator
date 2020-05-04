package com.example.fichaexercicios.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserLogin(val name: String, val email: String) : Parcelable {

    override fun toString(): String {
        return "Nome: $name, Email: $email"
    }

}