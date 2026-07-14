package com.yallahmasyaf.app.presentation.favorites

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
class FavoritesViewModel @Inject constructor(
    private val repository: CoastalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

    init {
        loadFavorites()
    }

    /**
     * Loads all favorite units from the repository asynchronously.
     */
    fun loadFavorites() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val favorites = repository.getFavoriteUnits()
                _uiState.update { it.copy(favoriteUnits = favorites, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    /**
     * Toggles the favorite status of a unit and reloads the favorites list.
     */
    fun toggleFavorite(unitId: String) {
        viewModelScope.launch {
            repository.toggleFavorite(unitId)
            loadFavorites()
        }
    }
}
