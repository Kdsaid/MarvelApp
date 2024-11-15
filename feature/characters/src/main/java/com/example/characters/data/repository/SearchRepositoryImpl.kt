package com.example.characters.data.repository

import com.example.characters.data.mappers.toHero

import com.example.characters.domain.model.Character
import com.example.characters.domain.usecase.SearchRepository
import com.example.data.network.CallInProgressException
import com.example.data.network.CharactersNetwork
import timber.log.Timber
import javax.inject.Inject

const val CHARACTERS_SEARCH_LIMIT = 20

class SearchRepositoryImpl @Inject constructor(
    private val network: CharactersNetwork,
) : SearchRepository {
    private var isRequestInProgress = false

    override suspend fun performSearch(
        nameStartsWith: String,
        page: Int,
    ): Result<List<Character>> {
        if (isRequestInProgress) {
            Timber.d("Request is in progress")
            return Result.failure(CallInProgressException("Request is in progress"))
        }
        Timber.d("Performing Network Search")
        val networkResult = performNetworkSearch(nameStartsWith, page)
        if (networkResult.isSuccess) {
            Timber.d("Returned results")
        }
        Timber.d("Returned page ${page - 1}")
        return networkResult
    }

    override suspend fun getCharacterDetails(heroId: Long): Result<Character?> {
        val heroDetails = network.getHeroDetails(heroId)
        if (heroDetails.isSuccess) {
            Timber.d("Returned hero details")
        }
        return network.getHeroDetails(heroId).map { it?.toHero() }
    }

    override suspend fun getThumbnail(comicId: Int): Result<String> {
        return network.getComicThumbnail(comicId)
    }

    private suspend fun performNetworkSearch(
        nameStartsWith: String,
        page: Int,
    ): Result<List<Character>> {
        isRequestInProgress = true
        val offset = (page - 1) * CHARACTERS_SEARCH_LIMIT
        val networkResult = network.searchHeroes(offset, CHARACTERS_SEARCH_LIMIT, nameStartsWith)
        isRequestInProgress = false
        return networkResult.map { result ->
            result.characters?.map { it.toHero() } ?: emptyList()
        }
    }
}
