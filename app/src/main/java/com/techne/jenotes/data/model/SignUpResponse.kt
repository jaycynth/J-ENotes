package com.techne.jenotes.data.model


data class SignUpResponse(
    val `data`: User,
    val message: String,
    val statusCode: Int
)