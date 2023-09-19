package com.marmatsan.core_domain.util

sealed interface UiEvent {
    data object Success : UiEvent
    data class ShowSnackBar(val message: UiText) : UiEvent
}