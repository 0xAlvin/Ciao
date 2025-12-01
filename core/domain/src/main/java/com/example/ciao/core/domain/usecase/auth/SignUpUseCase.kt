package com.example.ciao.core.domain.usecase.auth

import com.example.ciao.core.domain.repository.AuthRepoInterface
import User
import javax.inject.Inject
import com.example.ciao.common.result.Result
import com.example.ciao.core.domain.usecase.base.UseCase
import com.example.ciao.core.domain.model.valueobjects.isValidEmail

class SignUpUseCase @Inject constructor(
    private val authRepoInterface: AuthRepoInterface
) : UseCase<SignUpUseCase.Params, User>() {

    override suspend fun execute(parameters: Params): Result<User> {
        if (parameters.email.isEmpty()) {
            return Result.Error(
                message = "Email cannot be empty"
            )
        }
        if (!parameters.email.isValidEmail(parameters.email)) {
            return Result.Error(
                message = "Please enter a valid email address"
            )
        }
        if (parameters.password.length < 6) {
            return Result.Error(
                message = "Password must be at least 6 characters"
            )
        }

        return authRepoInterface.signUpWithEmail(
            email = parameters.email,
            password = parameters.password
        )
    }

    data class Params(
        val email: String,
        val password: String
    )
}
