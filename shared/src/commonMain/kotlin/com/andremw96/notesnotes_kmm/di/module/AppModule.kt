package com.andremw96.notesnotes_kmm.di.module

import org.koin.core.context.startKoin

// Common App Definitions
fun appModule() = listOf(repositoryModule, serviceModule, useCaseModule, platformModule)


