package com.example.characters.domain.usecase

import com.example.characters.domain.model.Character
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(
    private val repository: SearchRepository,
) {
    suspend operator fun invoke(
        characterId: Long,
    ): Result<Character?> =
        repository.getCharacterDetails(characterId)
}
