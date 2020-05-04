package com.example.fichaexercicios.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class User(val name: String, val email: String, val pass: String) : Parcelable {

    var uuid: String = UUID.randomUUID().toString()

    override fun toString(): String {
        return "Nome: $name, Email: $email"
    }

}