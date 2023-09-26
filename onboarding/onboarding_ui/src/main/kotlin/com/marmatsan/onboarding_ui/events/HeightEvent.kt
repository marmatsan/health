package com.marmatsan.onboarding_ui.events

sealed interface HeightEvent : Event {
    data class OnHeightChange(val height: String) : HeightEvent
    data object OnNextClicked : HeightEvent
}