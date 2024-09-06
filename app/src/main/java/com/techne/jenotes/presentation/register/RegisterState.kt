package com.techne.jenotes.presentation.register

import com.techne.jenotes.data.model.SignUpResponse

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Success(val signUpResponse: SignUpResponse) : RegisterState()
    data class Error(val message: String) : RegisterState()
}
