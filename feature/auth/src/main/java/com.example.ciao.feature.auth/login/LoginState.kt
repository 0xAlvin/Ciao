package com.example.ciao.feature.auth.login

import User

data class LoginUiState (
    val isLoading: Boolean = false,
    val error: String? = null,
    val loginSuccessful: Boolean = false,
    val user: User? = null
)
