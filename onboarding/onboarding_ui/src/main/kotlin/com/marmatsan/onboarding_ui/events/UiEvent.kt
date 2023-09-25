package com.marmatsan.onboarding_ui.events

import com.marmatsan.core_domain.util.UiText

sealed interface UiEvent {
    data object Success : UiEvent
    data class ShowSnackBar(val message: UiText) : UiEvent
}