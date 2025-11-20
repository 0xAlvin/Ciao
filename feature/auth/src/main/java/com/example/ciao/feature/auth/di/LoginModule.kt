package com.example.ciao.feature.auth.di

import com.example.ciao.core.data.repository.AuthRepository
import com.example.ciao.core.domain.repository.AuthRepoInterface
import com.example.ciao.core.domain.usecase.base.LoginUseCase
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
}