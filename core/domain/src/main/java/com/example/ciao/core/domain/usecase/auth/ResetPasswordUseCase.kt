package com.example.ciao.core.domain.usecase.auth

import com.example.ciao.core.domain.repository.AuthRepoInterface
import javax.inject.Inject
import com.example.ciao.common.result.Result
import com.example.ciao.core.domain.usecase.base.UseCase
import com.example.ciao.core.domain.model.valueobjects.isValidEmail

class ResetPasswordUseCase @Inject constructor(
    private val authRepoInterface: AuthRepoInterface
) : UseCase<String, Unit>() {

    override suspend fun execute(parameters: String): Result<Unit> {
        if (parameters.isEmpty()) {
            return Result.Error(
                message = "Email cannot be empty"
            )
        }
        if (!parameters.isValidEmail(parameters)) {
            return Result.Error(
                message = "Please enter a valid email address"
            )
        }

        return authRepoInterface.resetPassword(email = parameters)
    }
}
