package com.example.fichaexercicios.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class UserLogin(val name: String, val email: String) : Parcelable {

    override fun toString(): String {
        return "Nome: $name, Email: $email"
    }

}