package com.techne.jenotes.data.repository

import com.techne.jenotes.data.model.SignInRequest
import com.techne.jenotes.data.model.SignInResponse
import com.techne.jenotes.data.model.SignUpRequest
import com.techne.jenotes.data.model.SignUpResponse
import com.techne.jenotes.data.remote.AppService
import com.techne.jenotes.data.util.RemoteDataAccess
import com.techne.jenotes.data.util.RequestHelper
import com.techne.jenotes.domain.repository.AuthRepository
import com.techne.jenotes.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val appService: AppService,
    private val remoteDataAccess: RemoteDataAccess,
    private val requestHelper: RequestHelper,
) : AuthRepository {

    override suspend fun login(email: String, password: String): Flow<Resource<SignInResponse>> =
        remoteDataAccess.remoteCall {
            appService.login(
                requestHelper.createRequestBody(
                    SignInRequest(
                        email = email,
                        password = password
                    )
                )
            )
        }

    override suspend fun signup(
        firstName: String,
        lastName: String,
        username: String,
        email: String,
        password: String,
    ): Flow<Resource<SignUpResponse>> = remoteDataAccess.remoteCall {
        appService.register(
            requestHelper.createRequestBody(
                SignUpRequest(
                    firstName = firstName,
                    lastName = lastName,
                    emailAddress = email,
                    username = username,
                    password = password
                )
            )
        )
    }

}