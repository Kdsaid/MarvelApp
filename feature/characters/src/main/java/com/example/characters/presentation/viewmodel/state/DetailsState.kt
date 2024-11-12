package com.example.characters.presentation.viewmodel.state

import com.example.characters.domain.model.Character

sealed class DetailsState {
    object Loading : DetailsState()
    class Success(val character: Character) : DetailsState()
}
