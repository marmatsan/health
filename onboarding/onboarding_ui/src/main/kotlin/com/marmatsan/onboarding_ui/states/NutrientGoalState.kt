package com.marmatsan.onboarding_ui.states

import com.marmatsan.core_ui.state.State

data class NutrientGoalState(
    val carbsPct: String = "40",
    val proteinPct: String = "30",
    val fatPct: String = "30"
) : State