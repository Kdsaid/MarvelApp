package com.example.characters.data.mappers

import com.example.characters.domain.model.Character
import com.example.characters.domain.model.ComicSummary
import com.example.characters.domain.model.Link
import com.example.data.network.models.ComicSummaryDto
import com.example.data.network.models.HeroDto
import com.example.data.network.models.UrlDto
import com.example.data.network.models.toThumbnailUrl

fun HeroDto.toHero(): Character = Character(
    id = id,
    name = name.orEmpty(),
    description = description.orEmpty(),
    thumbnail = thumbnail?.toThumbnailUrl().orEmpty(),
    links = urls?.map { it.toUrl() }.orEmpty(),
    comics = comics?.items?.map { it.toComicSummary() }.orEmpty(),
)


fun UrlDto.toUrl(): Link =
    Link(
        type = type.orEmpty(),
        url = url.orEmpty(),
    )

fun ComicSummaryDto.toComicSummary(): ComicSummary =
    ComicSummary(
        title = name.orEmpty(),
        url = resourceUri.orEmpty(),
    )
