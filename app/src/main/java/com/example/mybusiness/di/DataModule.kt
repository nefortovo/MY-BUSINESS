package com.example.mybusiness.di

import com.example.mybusiness.data.repository.ToDoRepositoryImpl
import com.example.mybusiness.domain.repository.IToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideToDoRepository(
    ): IToDoRepository = ToDoRepositoryImpl()
}