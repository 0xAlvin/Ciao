package com.example.ciao.feature.auth.di

import com.example.ciao.core.data.repository.AuthRepository
import com.example.ciao.core.domain.repository.AuthRepoInterface
import com.example.ciao.core.domain.usecase.base.LoginUseCase
import com.example.ciao.core.domain.usecase.auth.SignUpUseCase
import com.example.ciao.core.domain.usecase.auth.ResetPasswordUseCase
import com.example.ciao.core.domain.usecase.auth.SignOutUseCase
import com.example.ciao.core.domain.usecase.auth.CheckAuthStatusUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepo(firebaseAuth: FirebaseAuth): AuthRepoInterface {
        return AuthRepository(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepoInterface: AuthRepoInterface): LoginUseCase {
        return LoginUseCase(authRepoInterface)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(authRepoInterface: AuthRepoInterface): SignUpUseCase {
        return SignUpUseCase(authRepoInterface)
    }

    @Provides
    @Singleton
    fun provideResetPasswordUseCase(authRepoInterface: AuthRepoInterface): ResetPasswordUseCase {
        return ResetPasswordUseCase(authRepoInterface)
    }

    @Provides
    @Singleton
    fun provideSignOutUseCase(authRepoInterface: AuthRepoInterface): SignOutUseCase {
        return SignOutUseCase(authRepoInterface)
    }

    @Provides
    @Singleton
    fun provideCheckAuthStatusUseCase(authRepoInterface: AuthRepoInterface): CheckAuthStatusUseCase {
        return CheckAuthStatusUseCase(authRepoInterface)
    }
}