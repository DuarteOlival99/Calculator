package com.example.fichaexercicios.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Operation(val exprexion: String, val result: Double){

    @PrimaryKey
    var uuid: String = UUID.randomUUID().toString()

    override fun toString(): String {
        return "$exprexion = $result"
    }


}