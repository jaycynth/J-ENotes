package com.techne.jenotes.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techne.jenotes.domain.repository.AuthRepository
import com.techne.jenotes.domain.util.Resource
import com.techne.jenotes.presentation.register.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun handleIntent(intent: LoginEvent) {
        when (intent) {
            is LoginEvent.Submit -> {
                if (validateInputs(email = intent.email, password = intent.password)) {
                    login(intent.email, intent.password)
                }
            }
        }
    }


    private fun validateInputs(email: String, password: String, ): Boolean {
        when {
            email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                _loginState.value = LoginState.Error("Please enter a valid email address.")
                return false
            }

            password.isBlank() -> {
                _loginState.value = LoginState.Error("Password cannot be empty.")
                return false
            }
            else -> {
                return true
            }
        }
    }


    private fun login(email: String, password: String) =
        viewModelScope.launch {
            authRepository.login(email, password).collect { resource ->
                when (resource) {
                    is Resource.Loading -> _loginState.value = LoginState.Loading
                    is Resource.Success -> _loginState.value = LoginState.Success(resource.data)
                    is Resource.Error -> _loginState.value = LoginState.Error(resource.error)
                    is Resource.Exception -> _loginState.value =
                        LoginState.Error("An exception occurred: ${resource.exception.message}")
                }
            }
        }
}



