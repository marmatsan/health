package com.marmatsan.onboarding_ui.states

import com.marmatsan.core_domain.model.Goal

data class WeightGoalState(
    val goal: Goal = Goal.KeepWeight
) : State