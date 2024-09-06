package com.techne.jenotes.presentation.login

import com.techne.jenotes.data.model.SignInResponse

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val signInResponse: SignInResponse) : LoginState()
    data class Error(val message: String) : LoginState()
}
