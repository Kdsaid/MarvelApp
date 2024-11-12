package com.example.characters.di

import com.example.characters.data.repository.SearchRepositoryImpl
import com.example.characters.domain.usecase.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CharactersRepositoryModule {
    @Binds
    abstract fun bindCharacterRepository(
        searchRepository: SearchRepositoryImpl,
    ): SearchRepository
}
