package com.example.common.presentation.deeplinks

import kotlinx.serialization.Serializable

interface Screen

@Serializable
data object CharactersScreen : Screen

@Serializable
data class CharacterDetails(val characterId: Long) : Screen

const val BASE_PATH = "destination://marvel"
