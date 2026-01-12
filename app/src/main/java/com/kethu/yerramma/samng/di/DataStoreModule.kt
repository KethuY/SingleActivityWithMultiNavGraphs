package com.kethu.yerramma.samng.di

import android.content.Context
import com.kethu.yerramma.samng.datastore.DataStoreRepository
import com.kethu.yerramma.samng.datastore.DataStoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class DataStoreModule {

    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository =
        DataStoreRepositoryImpl(context)
}
