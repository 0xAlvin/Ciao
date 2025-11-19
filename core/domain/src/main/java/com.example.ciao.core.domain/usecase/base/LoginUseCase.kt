package com.example.ciao.core.domain.usecase.base

import Email
import Password
import com.example.ciao.core.data.repository.AuthRepository
import User
import javax.inject.Inject
import com.example.ciao.common.result.Result
import isValid

class LoginUseCase @Inject constructor(
    private  val authRepository: AuthRepository
) : UseCase<LoginUseCase.Params, User>() {
    override suspend fun execute(parameters: Params): Result<User> {
        if (parameters.email.value.isEmpty()) {
            return Result.Error(
                message = "Email can not be empty"
            )
        }
        if (parameters.email.isValid(
                email = parameters.email
            )){
            return Result.Error(
                message = "Please enter a valid email address"
            )
        }
        if (parameters.email.value.length < 6) {
            return Result.Error(
                message = "Password must be at-least 6 characters"
            )
        }
        val response = authRepository.signInWithEmail(
            email = parameters.email.value,
            password = parameters.password.value
        )
        if (response.isError) {
            return Result.Error(
                message = "Login Failed"
            )
        }
        return response
    }

    data class Params (
        val email: Email,
        val password: Password
    )

}