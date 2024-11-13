package com.example.characters.characters.presentation

import com.example.characters.domain.model.Character

object FakeCharacters {
    fun getSampleCharacters(numCharacters: Int = 5): List<Character> =
        (1..numCharacters).map { characterId ->
            Character(
                id = characterId.toLong(),
                name = "character $characterId",
                description = "description $characterId",
                thumbnail = "",
                links = listOf(),
                comics = listOf(),
            )
        }
}
