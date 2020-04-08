package com.example.fichaexercicios

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(val name: String, val email: String, val pass: String) : Parcelable {
    override fun toString(): String {
        return "Nome: $name, Email: $email"
    }

}