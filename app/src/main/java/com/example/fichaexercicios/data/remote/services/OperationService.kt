package com.example.fichaexercicios.data.remote.services

import com.example.fichaexercicios.data.entity.Operation
import com.example.fichaexercicios.data.remote.requests.Operations
import com.example.fichaexercicios.data.remote.responses.OperationListResponse
import com.example.fichaexercicios.data.remote.responses.OperationResponse
import retrofit2.Response
import retrofit2.http.*

interface OperationService {

    @POST("operations")
    suspend fun addOperation(@Header ("Authorization") authorization: String, @Body body: Operations) : Response<OperationResponse>

    @DELETE("operations")
    suspend fun deleteOperation(@Header ("Authorization") authorization: String) : Response<OperationResponse>

    @GET("operations")
    suspend fun getOperation(@Header ("Authorization") authorization: String) : Response<List<Operations>>

}