package com.example.characters.domain.usecase

import com.example.characters.domain.model.Character

interface SearchRepository {
    suspend fun performSearch(nameStartsWith: String, page: Int): Result<List<Character>>
    suspend fun getCharacterDetails(heroId: Long): Result<Character?>
    suspend fun getThumbnail(comicId: Int): Result<String>
}
