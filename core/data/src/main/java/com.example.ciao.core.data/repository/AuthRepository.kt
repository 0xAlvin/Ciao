package com.example.ciao.core.data.repository


import AuthResult
import User
import com.example.ciao.core.domain.repository.AuthRepoInterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepoInterface {
    override suspend fun signInWithEmail(
        email: String,
        password: String
    ): AuthResult<User> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password)
            if (!result.isComplete){
                return AuthResult.Loading
            }
            val user = result.await().user
            return AuthResult.Success(
                User(
                    uid = user!!.uid,
                    email = user.email,
                    displayName = user.displayName
                )
            )
        }catch (e : FirebaseAuthException){
            AuthResult.Error("${e.message}Unknown Error Occurred")
        }
    }

    override suspend fun signUpWithEmail(
        email: String,
        password: String
    ): AuthResult<User> {
        TODO("Not yet implemented")
    }

    override suspend fun signOut(): AuthResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun resetPassword(email: String): AuthResult<Unit> {
        TODO("Not yet implemented")
    }

    override fun getCurrentUser(): Flow<User?> {
        TODO("Not yet implemented")
    }

    override fun isUserAuthenticated(): Boolean {
        TODO("Not yet implemented")
    }

}