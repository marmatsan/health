package com.marmatsan.onboarding_ui.events

sealed interface AgeEvent : Event {
    data class OnAgeChange(val age: String) : AgeEvent
    data object OnNextClick : AgeEvent
}