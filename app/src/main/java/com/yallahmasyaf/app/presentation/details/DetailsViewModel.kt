package com.yallahmasyaf.app.presentation.details

import androidx.lifecycle.SavedStateHandle
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
class DetailsViewModel @Inject constructor(
    private val repository: CoastalRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    init {
        // Extract the unitId passed via Navigation from SavedStateHandle
        val unitId: String? = savedStateHandle["unitId"]
        if (unitId != null) {
            loadUnitDetails(unitId)
        } else {
            _uiState.update { 
                it.copy(errorMessage = "معرّف الوحدة الساحلية غير صالح") 
            }
        }
    }

    /**
     * Loads coastal unit details by ID from the repository asynchronously.
     */
    fun loadUnitDetails(unitId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val unit = repository.getCoastalUnitById(unitId)
                if (unit != null) {
                    _uiState.update { it.copy(coastalUnit = unit, isLoading = false) }
                } else {
                    _uiState.update { 
                        it.copy(
                            isLoading = false, 
                            errorMessage = "عذراً، لم يتم العثور على الوحدة المطلوبة"
                        ) 
                    }
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        isLoading = false, 
                        errorMessage = e.localizedMessage ?: "حدث خطأ أثناء تحميل التفاصيل"
                    ) 
                }
            }
        }
    }
}
