package com.marmatsan.onboarding_ui.events

import com.marmatsan.core_domain.model.Goal

sealed interface GoalEvent : Event {
    data class OnGoalChange(val goal: Goal) : GoalEvent
    data object OnNextClicked : GoalEvent
}