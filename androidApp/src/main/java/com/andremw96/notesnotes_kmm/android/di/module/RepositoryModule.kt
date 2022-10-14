package com.andremw96.notesnotes_kmm.android.di.module

import com.andremw96.notesnotes_kmm.android.model.repository.LoginRepository
import com.andremw96.notesnotes_kmm.android.model.repository.impl.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideLoginRepository(): LoginRepository = LoginRepositoryImpl()
}
