package com.marmatsan.onboarding_ui.events

import com.marmatsan.core_domain.model.WeightGoal
import com.marmatsan.core_ui.event.Event

sealed interface WeightGoalEvent : Event {
    data class OnWeightGoalChange(val weightGoal: WeightGoal) : WeightGoalEvent
    data object OnNextClick : WeightGoalEvent
}