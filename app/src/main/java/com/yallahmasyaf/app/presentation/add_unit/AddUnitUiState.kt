package com.yallahmasyaf.app.presentation.add_unit

data class AddUnitUiState(
    val currentStep: Int = 1,
    val title: String = "",
    val pricePerNight: Double = 0.0,
    val city: String = "",
    val isLoading: Boolean = false
)
