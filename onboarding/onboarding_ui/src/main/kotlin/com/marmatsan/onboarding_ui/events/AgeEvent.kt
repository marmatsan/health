package com.marmatsan.onboarding_ui.events

sealed interface AgeEvent {
    data class OnAgeChange(val age: String) : AgeEvent
    data object OnNextClicked : AgeEvent
    data object OnBackClicked : AgeEvent
}