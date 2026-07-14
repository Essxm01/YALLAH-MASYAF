package com.yallahmasyaf.app.presentation.explore

import com.yallahmasyaf.app.domain.model.CoastalUnit

data class ExploreUiState(
    val units: List<CoastalUnit> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val selectedCity: String = "الكل",
    val selectedMaxPrice: Float = 30000f,
    val searchQuery: String = ""
)
