package com.example.ciao.core.data.repository


import AuthResult
import User
import com.example.ciao.core.domain.repository.AuthRepoInterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
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
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user

            if (firebaseUser != null) {
                AuthResult.Success(
                    User(
                        uid = firebaseUser.uid,
                        email = firebaseUser.email,
                        displayName = firebaseUser.displayName
                    )
                )
            } else {
                AuthResult.Error("Sign up failed")
            }
        } catch (e: FirebaseAuthException) {
            AuthResult.Error(e.message ?: "Unknown error occurred")
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Unknown error occurred")
        }
    }

    override suspend fun signOut(): AuthResult<Unit> {
        return try {
            firebaseAuth.signOut()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to sign out")
        }
    }

    override suspend fun resetPassword(email: String): AuthResult<Unit> {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            AuthResult.Success(Unit)
        } catch (e: FirebaseAuthException) {
            AuthResult.Error(e.message ?: "Failed to send reset email")
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Unknown error occurred")
        }
    }

    override fun getCurrentUser(): Flow<User?> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            val firebaseUser = auth.currentUser
            trySend(
                firebaseUser?.let {
                    User(
                        uid = it.uid,
                        email = it.email,
                        displayName = it.displayName
                    )
                }
            )
        }

        firebaseAuth.addAuthStateListener(authStateListener)

        awaitClose {
            firebaseAuth.removeAuthStateListener(authStateListener)
        }
    }


    override fun isUserAuthenticated(): Boolean {
        return firebaseAuth.currentUser != null
    }

}