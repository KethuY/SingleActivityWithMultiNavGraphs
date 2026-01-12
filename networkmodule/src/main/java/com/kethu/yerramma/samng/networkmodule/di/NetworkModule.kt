package com.kethu.yerramma.samng.networkmodule.di

import android.content.Context
import com.kethu.yerramma.samng.networkmodule.client.ApiService
import com.kethu.yerramma.samng.networkmodule.client.DispatcherProvider
import com.kethu.yerramma.samng.networkmodule.client.NetworkDataSource
import com.kethu.yerramma.samng.networkmodule.client.NetworkDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * @Author: Yerramma Kethu
 * @Date: 22/12/2025
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideNetworkDataSource(
        @ApplicationContext context: Context,
        dispatcher: DispatcherProvider,
        apiInterface: ApiService
    ): NetworkDataSource =
        NetworkDataSourceImpl(context, dispatcher, apiInterface)
}