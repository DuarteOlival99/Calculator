package com.example.fichaexercicios.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fichaexercicios.data.entity.Operation

@Dao
interface OperationDao {

    @Insert
    suspend fun insert(operation: Operation)

    @Query("SELECT * FROM operation")
    suspend fun getAll(): List<Operation>

}