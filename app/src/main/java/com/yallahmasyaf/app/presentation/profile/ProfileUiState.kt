package com.yallahmasyaf.app.presentation.profile

import com.yallahmasyaf.app.domain.model.CoastalUnit

data class ProfileUiState(
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val currentRole: UserRole = UserRole.RENTER,
    val ownerUnits: List<CoastalUnit> = emptyList(),
    val isLoading: Boolean = false
)
