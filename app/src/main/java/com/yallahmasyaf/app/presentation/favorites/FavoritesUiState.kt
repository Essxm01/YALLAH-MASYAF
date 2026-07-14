package com.yallahmasyaf.app.presentation.favorites

import com.yallahmasyaf.app.domain.model.CoastalUnit

data class FavoritesUiState(
    val favoriteUnits: List<CoastalUnit> = emptyList(),
    val isLoading: Boolean = false
)
