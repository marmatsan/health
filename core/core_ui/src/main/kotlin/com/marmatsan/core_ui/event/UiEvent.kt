package com.marmatsan.core_ui.event

import com.marmatsan.core_domain.util.UiText

sealed interface UiEvent {
    data object Success : UiEvent
    data class ShowSnackBar(val message: UiText) : UiEvent
}