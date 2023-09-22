package com.marmatsan.onboarding_ui

import com.marmatsan.core_domain.util.UiText

sealed interface UiEvent {
    data object Success : UiEvent
    data object NavigateBack : UiEvent
    data class ShowSnackBar(val message: UiText) : UiEvent
}