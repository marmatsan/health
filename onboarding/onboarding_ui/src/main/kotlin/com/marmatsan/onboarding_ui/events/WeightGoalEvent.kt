package com.marmatsan.onboarding_ui.events

import com.marmatsan.core_domain.model.Goal
import com.marmatsan.core_ui.event.Event

sealed interface WeightGoalEvent : Event {
    data class OnWeightGoalChange(val goal: Goal) : WeightGoalEvent
    data object OnNextClick : WeightGoalEvent
}