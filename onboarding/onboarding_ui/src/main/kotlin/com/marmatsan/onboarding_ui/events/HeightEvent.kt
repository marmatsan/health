package com.marmatsan.onboarding_ui.events

import com.marmatsan.core_ui.event.Event

sealed interface HeightEvent : Event {
    data class OnHeightChange(val height: String) : HeightEvent
    data object OnNextClick : HeightEvent
}