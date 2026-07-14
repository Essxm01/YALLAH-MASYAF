package com.yallahmasyaf.app.presentation.details

import com.yallahmasyaf.app.domain.model.CoastalUnit

data class DetailsUiState(
    val coastalUnit: CoastalUnit? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
