package com.yallahmasyaf.app.presentation.add_unit

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddUnitViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(AddUnitUiState())
    val uiState: StateFlow<AddUnitUiState> = _uiState.asStateFlow()

    /**
     * Navigates to the next wizard step (max step 2).
     */
    fun nextStep() {
        _uiState.update {
            val next = if (it.currentStep < 2) it.currentStep + 1 else it.currentStep
            it.copy(currentStep = next)
        }
    }

    /**
     * Navigates to the previous wizard step (min step 1).
     */
    fun previousStep() {
        _uiState.update {
            val prev = if (it.currentStep > 1) it.currentStep - 1 else it.currentStep
            it.copy(currentStep = prev)
        }
    }
}
