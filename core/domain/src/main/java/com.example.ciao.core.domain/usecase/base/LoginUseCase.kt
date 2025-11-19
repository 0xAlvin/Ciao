package com.example.ciao.core.domain.usecase.base

import com.example.ciao.core.data.repository.AuthRepository

class LoginUseCase @Inject constructor(
    private  val authRepository: AuthRepository
) : UseCase<LoginUseCase.Params, User> {
    override suspend fun execute(parameters: Params): Result<User> {
        TODO("Not yet implemented")
    }

    data class Params (
        val email: Email,
        val password: Password
    )
}