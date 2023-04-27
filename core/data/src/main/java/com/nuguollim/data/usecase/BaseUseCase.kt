package com.nuguollim.data.usecase

abstract class BaseUseCase<in Params, out Type> {

    abstract fun run(params: Params): Type

    operator fun invoke(params: Params) = run(params)

}