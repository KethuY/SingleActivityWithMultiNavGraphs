package com.kethu.yerramma.samng.networkmodule.client

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

interface DispatcherProvider {
    @MainDispatcher
    fun main(): CoroutineDispatcher = Dispatchers.Main
    @DefaultDispatcher
    fun default(): CoroutineDispatcher = Dispatchers.Default
    @IoDispatcher
    fun io(): CoroutineDispatcher = Dispatchers.IO
    @UnConfined
    fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined

}
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class UnConfined

open class DefaultDispatcherProvider : DispatcherProvider