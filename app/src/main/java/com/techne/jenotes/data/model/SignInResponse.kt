package com.techne.jenotes.data.model


data class SignInResponse(
    val `data`: Token,
    val message: String,
    val statusCode: Int
)