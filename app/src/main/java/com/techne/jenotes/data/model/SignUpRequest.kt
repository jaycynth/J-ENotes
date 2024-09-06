package com.techne.jenotes.data.model

data class SignUpRequest(
    val emailAddress: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val username: String
)