package com.example.ciao.core.domain.usecase.base

import kotlinx.coroutines.flow.Flow
import com.example.ciao.common.result.Result

abstract class UseCase<in P, out R> {
    protected abstract suspend fun execute(parameters: P): com.example.ciao.common.result.Result<R>
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