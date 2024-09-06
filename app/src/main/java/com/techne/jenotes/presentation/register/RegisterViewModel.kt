package com.techne.jenotes.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techne.jenotes.domain.repository.AuthRepository
import com.techne.jenotes.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()


    fun handleIntent(intent: RegisterEvent) {
        when (intent) {
            is RegisterEvent.Submit -> {
                val nameParts = intent.fullName.trim().split("\\s+".toRegex(), limit = 2)
                val firstName = nameParts[0]
                val lastName = nameParts.getOrElse(1) { "" }

                if (validateInputs(
                        firstName = firstName,
                        lastName = lastName,
                        username = intent.userName,
                        email = intent.email,
                        password = intent.password,
                        confirmPassword = intent.confirmPassword
                    )
                ) {
                    register(firstName, lastName, intent.userName, intent.email, intent.password)
                }
            }
        }
    }

    private fun validateInputs(
        firstName: String,
        lastName: String,
        username: String,
        email: String,
        password: String,
        confirmPassword: String,
    ): Boolean {
        when {
            firstName.isBlank() -> {
                _registerState.value = RegisterState.Error("First name cannot be empty.")
                return false
            }

            lastName.isBlank() -> {
                _registerState.value = RegisterState.Error("Last name cannot be empty. Enter fullName")
                return false
            }

            username.isBlank() -> {
                _registerState.value = RegisterState.Error("Username cannot be empty.")
                return false
            }

            email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                _registerState.value = RegisterState.Error("Please enter a valid email address.")
                return false
            }

            password.isBlank() -> {
                _registerState.value = RegisterState.Error("Password cannot be empty.")
                return false
            }

            password != confirmPassword -> {
                _registerState.value = RegisterState.Error("Passwords do not match.")
                return false
            }

            else -> {
                return true
            }
        }
    }

    private fun register(
        firstName: String,
        lastName: String,
        username: String,
        email: String,
        password: String,
    ) =
        viewModelScope.launch {
            authRepository.signup(
                firstName = firstName, lastName = lastName, username = username,
                email = email, password = password
            ).collect { resource ->
                when (resource) {
                    is Resource.Loading -> _registerState.value = RegisterState.Loading
                    is Resource.Success -> _registerState.value =
                        RegisterState.Success(resource.data)

                    is Resource.Error -> _registerState.value = RegisterState.Error(resource.error)
                    is Resource.Exception -> _registerState.value =
                        RegisterState.Error("An exception occurred: ${resource.exception.message}")
                }
            }
        }
}