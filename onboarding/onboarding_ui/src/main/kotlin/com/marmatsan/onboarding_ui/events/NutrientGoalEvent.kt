package com.marmatsan.onboarding_ui.events

import com.marmatsan.core_ui.event.Event

sealed interface NutrientGoalEvent : Event {
    data class OnCarbPctChange(val carbPct: String) : NutrientGoalEvent
    data class OnProteinPctChange(val proteinPct: String) : NutrientGoalEvent
    data class OnFatPctChange(val fatPct: String) : NutrientGoalEvent
    data object OnNextClick : NutrientGoalEvent
}