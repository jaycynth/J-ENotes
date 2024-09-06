package com.techne.jenotes.data.model

data class User(
    val createdAt: String,
    val emailAddress: String,
    val emailVerified: Boolean,
    val firstName: String,
    val id: String,
    val isAdmin: Boolean,
    val lastName: String,
    val refreshToken: String? = null,
    val updatedAt: String,
    val username: String
)