package com.example.characters.domain.usecase

import com.example.characters.domain.model.Character
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: SearchRepository,
) {
    suspend operator fun invoke(nameStart: String, page: Int): Result<List<Character>> =
        repository.performSearch(nameStart, page)
}
