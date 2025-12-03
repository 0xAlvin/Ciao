package com.example.ciao.feature.auth.presentation.forgotpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ciao.common.result.Result
import com.example.ciao.core.domain.usecase.auth.ResetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState: StateFlow<ForgotPasswordUiState> = _uiState.asStateFlow()

    fun onEvent(event: ForgotPasswordEvent) {
        when (event) {
            is ForgotPasswordEvent.EmailChanged -> {
                _uiState.update { it.copy(email = event.email, error = null) }
            }
            ForgotPasswordEvent.SendResetLinkClicked -> {
                sendResetLink()
            }
            ForgotPasswordEvent.ErrorDismissed -> {
                _uiState.update { it.copy(error = null) }
            }
            ForgotPasswordEvent.SuccessDismissed -> {
                _uiState.update { it.copy(isResetEmailSent = false) }
            }
        }
    }

    private fun sendResetLink() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = resetPasswordUseCase(_uiState.value.email)
            when (result) {
                is Result.Success -> {
                    _uiState.update { it.copy(isLoading = false, isResetEmailSent = true) }
                }
                is Result.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = result.message) }
                }
                Result.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}

data class ForgotPasswordUiState(
    val email: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isResetEmailSent: Boolean = false
)

sealed interface ForgotPasswordEvent {
    data class EmailChanged(val email: String) : ForgotPasswordEvent
    data object SendResetLinkClicked : ForgotPasswordEvent
    data object ErrorDismissed : ForgotPasswordEvent
    data object SuccessDismissed : ForgotPasswordEvent
}
