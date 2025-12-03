package com.example.ciao.core.domain.usecase.auth

import com.example.ciao.core.domain.repository.AuthRepoInterface
import javax.inject.Inject

class CheckAuthStatusUseCase @Inject constructor(
    private val authRepoInterface: AuthRepoInterface
) {
    operator fun invoke(): Boolean {
        return authRepoInterface.isUserAuthenticated()
    }
}
