package com.example.ciao.core.domain.usecase.auth

import com.example.ciao.core.domain.repository.AuthRepoInterface
import javax.inject.Inject
import com.example.ciao.common.result.Result
import com.example.ciao.core.domain.usecase.base.UseCase

class SignOutUseCase @Inject constructor(
    private val authRepoInterface: AuthRepoInterface
) : UseCase<Unit, Unit>() {

    override suspend fun execute(parameters: Unit): Result<Unit> {
        return authRepoInterface.signOut()
    }
}
