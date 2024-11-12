package com.example.data.di

import com.example.data.network.CharactersNetwork
import com.example.data.network.CharactersNetworkImpl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    @Singleton
    abstract fun bindCharactersNetwork(
        marvelNetwork: CharactersNetworkImpl,
    ): CharactersNetwork

}
