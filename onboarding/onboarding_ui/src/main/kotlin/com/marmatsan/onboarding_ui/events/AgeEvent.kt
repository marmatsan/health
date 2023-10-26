package com.marmatsan.onboarding_ui.events

import com.marmatsan.core_ui.event.Event

sealed interface AgeEvent : Event {
    data class OnAgeChange(val age: String) : AgeEvent
    data object OnNextClick : AgeEvent
}