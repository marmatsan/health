package com.marmatsan.onboarding_ui.events

import com.marmatsan.core_ui.event.Event

sealed interface WeightEvent : Event {
    data class OnWeightChange(val weight: String) : WeightEvent
    data object OnNextClick : WeightEvent
}