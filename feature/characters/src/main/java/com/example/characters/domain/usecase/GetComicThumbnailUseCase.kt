package com.example.characters.domain.usecase

import javax.inject.Inject

class GetComicThumbnailUseCase @Inject constructor(
    private val repository: SearchRepository,
) {
    suspend operator fun invoke(comicId: Int): Result<String> =
        repository.getThumbnail(comicId)
}
