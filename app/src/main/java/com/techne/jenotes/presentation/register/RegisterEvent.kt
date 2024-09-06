package com.techne.jenotes.presentation.register


sealed class RegisterEvent {
    data class Submit(
        val fullName: String,
        val confirmPassword: String,
        val userName: String,
        val email: String,
        val password: String,
    ) : RegisterEvent()
}