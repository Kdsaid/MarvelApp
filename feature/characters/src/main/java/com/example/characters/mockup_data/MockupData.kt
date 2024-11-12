package com.example.characters.mockup_data

import com.example.characters.data.mappers.toHero
import com.example.data.network.models.ComicSummaryDto
import com.example.data.network.models.ComicsDto
import com.example.data.network.models.HeroDto
import com.example.data.network.models.ImageDto
import com.example.data.network.models.UrlDto


object MockupData {
    val heroesDtoMockup = (1..10).map {
        HeroDto(
            id = it.toLong(),
            name = "Hero$it",
            description = "Description hero $it",
            thumbnail = ImageDto(
                path = "http://lorempixel.com/150/150/people/$it/",
                extension = "jpg",
            ),
            urls = listOf(
                UrlDto(
                    type = "Detail",
                    url = "DetailUrl",
                ),
                UrlDto(
                    type = "Wiki",
                    url = "wikiUrl",
                ),
                UrlDto(
                    type = "ComicLink",
                    url = "comicLink",
                ),
            ),
            comics = ComicsDto(
                available = 2,
                returned = 2,
                collectionUri = "collectionUri",
                items = (1..2).map {
                    ComicSummaryDto(
                        name = "comic $it",
                        resourceUri = "url/comic/$it",
                    )
                },
            ),
        )
    }


    val heroesSample = heroesDtoMockup.map { it.toHero() }

    val heroesSampleWithoutImages = heroesSample.map { hero ->
        hero.copy(
            thumbnail = "",
            comics = hero.comics?.map {
                it.copy(thumbnail = "")
            },
        )
    }
}
