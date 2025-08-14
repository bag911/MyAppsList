package com.example.myapps.di

import com.example.myapps.domain.AppListRepository
import com.example.myapps.data.AppListListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindAppsRepository(
        impl: AppListListRepositoryImpl
    ): AppListRepository
}