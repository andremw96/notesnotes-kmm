package com.andremw96.notesnotes_kmm.android.di.module

import com.andremw96.notesnotes_kmm.model.LoginDataValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedModule {
    @Singleton
    @Provides
    fun provideLoginDataValidator(): LoginDataValidator = LoginDataValidator()
}
