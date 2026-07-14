package com.yallahmasyaf.app.presentation.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yallahmasyaf.app.domain.model.CoastalUnit
import com.yallahmasyaf.app.domain.repository.CoastalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val repository: CoastalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExploreUiState())
    val uiState: StateFlow<ExploreUiState> = _uiState.asStateFlow()

    // Local cache for unfiltered coastal units to optimize filter recalculations
    private var allUnits: List<CoastalUnit> = emptyList()

    init {
        loadCoastalUnits()
    }

    /**
     * Loads all coastal units asynchronously.
     */
    fun loadCoastalUnits() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                allUnits = repository.getCoastalUnits()
                applyFilters()
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        isLoading = false, 
                        errorMessage = e.localizedMessage ?: "حدث خطأ غير متوقع أثناء تحميل البيانات"
                    ) 
                }
            }
        }
    }

    /**
     * Updates selected city filter and applies constraints.
     */
    fun onCitySelected(city: String) {
        _uiState.update { it.copy(selectedCity = city) }
        applyFilters()
    }

    /**
     * Updates selected maximum price filter and applies constraints.
     */
    fun onPriceChanged(maxPrice: Float) {
        _uiState.update { it.copy(selectedMaxPrice = maxPrice) }
        applyFilters()
    }

    /**
     * Updates textual search query and applies constraints.
     */
    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        applyFilters()
    }

    /**
     * Toggles the favorite status of a coastal unit and refreshes the list.
     */
    fun toggleFavorite(unitId: String) {
        viewModelScope.launch {
            repository.toggleFavorite(unitId)
            allUnits = repository.getCoastalUnits()
            applyFilters()
        }
    }

    /**
     * Evaluates active filters (city, price, query) against the local cache.
     */
    private fun applyFilters() {
        val currentState = _uiState.value
        val filtered = allUnits.filter { unit ->
            val matchesCity = currentState.selectedCity == "الكل" || 
                    unit.city.equals(currentState.selectedCity, ignoreCase = true)
            
            val matchesPrice = unit.pricePerNight <= currentState.selectedMaxPrice.toDouble()
            
            val matchesSearch = currentState.searchQuery.isBlank() || 
                    unit.title.contains(currentState.searchQuery, ignoreCase = true) || 
                    unit.description.contains(currentState.searchQuery, ignoreCase = true) ||
                    unit.district.contains(currentState.searchQuery, ignoreCase = true)
            
            matchesCity && matchesPrice && matchesSearch
        }
        _uiState.update { it.copy(units = filtered, isLoading = false) }
    }
}
