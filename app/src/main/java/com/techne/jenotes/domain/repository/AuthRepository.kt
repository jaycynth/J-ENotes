package com.techne.jenotes.domain.repository

import com.techne.jenotes.data.model.SignInResponse
import com.techne.jenotes.data.model.SignUpResponse
import com.techne.jenotes.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AuthRepository {

    suspend fun login(email: String, password: String): Flow<Resource<SignInResponse>>
    suspend fun signup(
        firstName: String,
        lastName: String,
        username: String,
        email: String,
        password: String,
    ): Flow<Resource<SignUpResponse>>

}