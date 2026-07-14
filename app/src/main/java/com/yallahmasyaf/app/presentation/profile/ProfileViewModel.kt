package com.yallahmasyaf.app.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yallahmasyaf.app.domain.repository.CoastalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: CoastalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                // Fetch units to mock current owner's properties
                val allUnits = repository.getCoastalUnits()
                val mockOwnerUnits = allUnits.take(2) // Mock first 2 units as owned by this user
                
                _uiState.update {
                    it.copy(
                        name = "أحمد محمد علي",
                        phone = "+20 100 123 4567",
                        email = "ahmed.mohamed@example.com",
                        currentRole = UserRole.RENTER,
                        ownerUnits = mockOwnerUnits,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        name = "أحمد محمد علي",
                        phone = "+20 100 123 4567",
                        email = "ahmed.mohamed@example.com",
                        currentRole = UserRole.RENTER,
                        ownerUnits = emptyList(),
                        isLoading = false
                    )
                }
            }
        }
    }

    /**
     * Toggles the user role between RENTER and OWNER and publishes the state update.
     */
    fun toggleRole() {
        _uiState.update {
            val newRole = if (it.currentRole == UserRole.RENTER) UserRole.OWNER else UserRole.RENTER
            it.copy(currentRole = newRole)
        }
    }
}
