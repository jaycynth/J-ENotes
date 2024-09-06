package com.techne.jenotes.presentation.login

sealed class LoginEvent {
    data class Submit(val email: String, val password: String) : LoginEvent()
}