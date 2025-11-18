package com.example.ciao.core.domain.repository

import AuthResult
import User
import kotlinx.coroutines.flow.Flow

interface AuthRepoInterface {
    suspend fun signInWithEmail(email: String, password: String): AuthResult<User>
    suspend fun signUpWithEmail(email: String, password: String): AuthResult<User>
    suspend fun signOut(): AuthResult<Unit>
    suspend fun resetPassword(email: String): AuthResult<Unit>
    fun getCurrentUser(): Flow<User?>
    fun isUserAuthenticated(): Boolean
}
