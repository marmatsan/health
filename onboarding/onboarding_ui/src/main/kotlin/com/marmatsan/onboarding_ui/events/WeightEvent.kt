package com.marmatsan.onboarding_ui.events

sealed interface WeightEvent : Event {
    data class OnWeightChange(val weight: String) : WeightEvent
    data object OnNextClicked : WeightEvent
}