package com.techne.jenotes.data.remote

import com.techne.jenotes.data.model.SignInResponse
import com.techne.jenotes.data.model.SignUpResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AppService {

    @POST("auth/login")
    suspend fun login(
        @Body requestBody: RequestBody,
    ): Response<SignInResponse>

    @POST("auth/signup")
    suspend fun register(
        @Body requestBody: RequestBody,
        ): Response<SignUpResponse>

    @POST("notes")
    suspend fun addNotes(): SignUpResponse

    @GET("notes")
    suspend fun listNotes(): SignUpResponse
}