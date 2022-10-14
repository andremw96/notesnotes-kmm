package com.andremw96.notesnotes_kmm.android.di.module

import com.andremw96.notesnotes_kmm.android.domain.Login
import com.andremw96.notesnotes_kmm.android.domain.impl.LoginImpl
import com.andremw96.notesnotes_kmm.android.model.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideLogin(
        loginRepository: LoginRepository,
    ): Login = LoginImpl(loginRepository)
}
