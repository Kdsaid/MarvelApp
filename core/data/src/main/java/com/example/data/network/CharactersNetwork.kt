package com.example.data.network

import com.example.data.network.models.HeroDto
import com.example.data.network.models.PaginationInfo

interface CharactersNetwork {
    suspend fun searchHeroes(offset: Int, limit: Int, nameStartsWith: String): Result<HeroesResult>

    suspend fun getHeroDetails(heroId: Long): Result<HeroDto?>

    suspend fun getComicThumbnail(comicId: Int): Result<String>
}

data class HeroesResult(val paginationInfo: PaginationInfo, val characters: List<HeroDto>?)
