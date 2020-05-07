package com.example.fichaexercicios.data.remote.requests


data class Operations(private val uuid: String, private val expression: String, private val result: Double) {


    fun getExpression(): String {
        return expression
    }

    fun getResult(): Double {
        return result
    }

    override fun toString(): String {
        return "Operations(uuid='$uuid', exprexion='$expression', result=$result)"
    }

}