package com.kethu.yerramma.samng.featuredashboard.di

import com.kethu.yerramma.samng.featuredashboard.repo.TodoRepository
import com.kethu.yerramma.samng.featuredashboard.repo.TodoRepositoryImpl
import com.kethu.yerramma.samng.networkmodule.client.NetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author: Yerramma Kethu
 * @Date: 22/12/2025
 */
@Module
@InstallIn(SingletonComponent::class)
object DashboardRepositoryModule {
    @Singleton
    @Provides
    fun providePostRepository(dataSource: NetworkDataSource): TodoRepository =
        TodoRepositoryImpl(dataSource)
}