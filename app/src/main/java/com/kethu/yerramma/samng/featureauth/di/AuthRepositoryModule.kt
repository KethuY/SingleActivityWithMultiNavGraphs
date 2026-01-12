package com.kethu.yerramma.samng.featureauth.di

import com.kethu.yerramma.samng.featureauth.repository.AuthRepository
import com.kethu.yerramma.samng.featureauth.repository.AuthRepositoryImpl
import com.kethu.yerramma.samng.networkmodule.client.NetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthRepositoryModule {
    @Singleton
    @Provides
    fun providePostRepository(dataSource: NetworkDataSource): AuthRepository =
        AuthRepositoryImpl(dataSource)
}