package com.marmatsan.core_ui.utils

sealed interface UiEvent {
    data class Navigate(val route: String) : UiEvent
    data object NavigateDown : UiEvent
    data object NavigateUp : UiEvent
    data class ShowSnackBar(val message: UiText) : UiEvent
}