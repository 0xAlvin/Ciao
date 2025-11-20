package com.example.ciao.feature.auth.login

import Email
import Password
import com.example.ciao.common.result.Result
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ciao.core.domain.usecase.base.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onLoginClick(email: Email, password: Password) {
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val result = loginUseCase(
                LoginUseCase.Params(
                    email = email,
                    password = password
                )
            )

            when (result) {
                is Result.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            loginSuccessful = true,
                            user = result.data
                        )
                    }
                }

                is Result.Error -> {
                    _uiState.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }

                else -> {
                    _uiState.update {
                        it.copy(isLoading = false, error = "Unknown state encountered")
                    }
                }
            }
        }
    }
}