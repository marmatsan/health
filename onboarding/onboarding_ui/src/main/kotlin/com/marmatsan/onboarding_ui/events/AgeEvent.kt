package com.marmatsan.onboarding_ui.events

sealed interface AgeEvent : UiEvent {
    data class OnAgeChange(val age: String) : AgeEvent
    data object OnNextClicked : AgeEvent
}