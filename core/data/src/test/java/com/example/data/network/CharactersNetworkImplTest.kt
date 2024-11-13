package com.example.data.network


import com.example.data.network.models.ComicDataContainer
import com.example.data.network.models.ComicDto
import com.example.data.network.models.ComicResults
import com.example.data.network.models.HeroDto
import com.example.data.network.models.HeroResults
import com.example.data.network.models.ImageDto
import com.example.data.network.models.SearchData
import com.example.test_libary.TestCoroutineExtension
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestCoroutineExtension::class)
internal class CharactersNetworkImplTest {
    @MockK
    private lateinit var marvelService: MarvelService
    private val charactersNetwork: CharactersNetwork
    private val comicId = 1
    private val offset = 0
    private val limit = 20

    init {
        MockKAnnotations.init(this)
        charactersNetwork = CharactersNetworkImpl(marvelService)
    }

    @Test
    fun `Successful response performing search returns Success`() =
        runTest {
            `given a response is returned when searchHeroes gets called on the service`()

            val response = `when searchHeroes gets called on the network`()

            `then the response should be of type Success`(response)
            `then there should be one element in the returned data`(response)
        }

    @Test
    fun `Response without data performing search returns Error`() =
        runTest {
            `given a response with no data is returned when searchHeroes gets called on the service`()

            val response = `when searchHeroes gets called on the network`()

            `then the response should be of type Error`(response)
        }

    @Test
    fun `Error performing search returns Error`() =
        runTest {
            `given an exception is thrown when searchHeroes gets called on the service`()

            val response = `when searchHeroes gets called on the network`()

            `then the response should be of type Error`(response)
        }

    @Test
    fun `Successful response performing getComicThumbnail returns Success`() =
        runTest {
            `given a response is returned when searchComic gets called on the service`()

            val response = `when getComicThumbnail gets called on the network`()

            `then the searchComic response should be of type Success`(response)
            `then there should be one element in the returned data from searchComic`(response)
        }

    @Test
    fun `Error performing getComicThumbnail returns Error`() =
        runTest {
            `given an exception is thrown when searchComic gets called on the service`()

            val response = `when getComicThumbnail gets called on the network`()

            `then the getComicThumbnail response should be of type Error`(response)
        }

    private fun `given a response is returned when searchHeroes gets called on the service`() {
        coEvery { marvelService.searchHeroes(offset = offset, limit = limit) } returns
            HeroResults(
                data = SearchData(
                    offset = 0,
                    limit = limit,
                    total = 1,
                    count = 1,
                    results = listOf(
                        HeroDto(
                            id = 0,
                            name = "Batman",
                            thumbnail = ImageDto(
                                "path",
                                "extension",
                            ),
                        ),
                    ),
                ),
            )
    }

    private fun `given a response with no data is returned when searchHeroes gets called on the service`() {
        coEvery { marvelService.searchHeroes(offset = offset, limit = limit) } returns
            HeroResults()
    }

    private fun `given a response is returned when searchComic gets called on the service`() {
        coEvery { marvelService.searchComic(comicId) } returns
            ComicResults(
                data = ComicDataContainer(
                    results = listOf(
                        ComicDto(
                            id = 123,
                            title = "title",
                            thumbnail = ImageDto(
                                "path",
                                "extension",
                            ),
                        ),
                    ),
                ),
            )
    }

    private fun `given an exception is thrown when searchHeroes gets called on the service`() {
        coEvery { marvelService.searchHeroes() } throws Exception()
    }

    private fun `given an exception is thrown when searchComic gets called on the service`() {
        coEvery { marvelService.searchComic(any()) } throws Exception()
    }

    private suspend fun `when searchHeroes gets called on the network`(): Result<HeroesResult> {
        return charactersNetwork.searchHeroes(offset, limit, "")
    }

    private suspend fun `when getComicThumbnail gets called on the network`(): Result<String> {
        return charactersNetwork.getComicThumbnail(comicId)
    }

    private fun `then the response should be of type Success`(response: Result<HeroesResult>) {
        assertTrue(response.isSuccess)
    }

    private fun `then the searchComic response should be of type Success`(response: Result<String>) {
        assertTrue(response.isSuccess)
    }

    private fun `then there should be one element in the returned data`(response: Result<HeroesResult>) {
        assertEquals(Result.success(1), response.map { it.characters!!.size })
    }

    private fun `then there should be one element in the returned data from searchComic`(response: Result<String>) {
        assertEquals(Result.success("path.extension"), response.map { it })
    }

    private fun `then the response should be of type Error`(response: Result<HeroesResult>) {
        assertTrue(response.isFailure)
    }

    private fun `then the getComicThumbnail response should be of type Error`(response: Result<String>) {
        assertTrue(response.isFailure)
    }
}
