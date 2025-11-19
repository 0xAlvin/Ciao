package com.example.ciao.core.domain.usecase.base

import kotlinx.coroutines.flow.Flow

abstract class UseCase<in P, out R> {
    protected abstract suspend fun execute(parameters: P): Result<R>
    suspend operator fun invoke(params: P): Result<R> {
        return execute(params)
    }
}

abstract class FlowUseCase<in P, out R> {
    protected abstract suspend fun execute(parameters: P): Flow<Result<R>>
    suspend operator fun invoke(params: P): Flow<Result<R>> {
        return execute(params)
    }
}