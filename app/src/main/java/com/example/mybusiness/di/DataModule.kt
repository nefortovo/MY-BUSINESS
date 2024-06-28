package com.example.mybusiness.di

import com.example.mybusiness.data.repository.ToDoRepositoryImpl
import com.example.mybusiness.domain.interactor.impl.ToDoInteractorImpl
import com.example.mybusiness.domain.repository.IToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideToDoRepository(
    ): IToDoRepository = ToDoRepositoryImpl()
}