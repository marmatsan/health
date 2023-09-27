package com.marmatsan.onboarding_ui.states

import com.marmatsan.core_domain.model.Goal

data class GoalState(
    val goal: Goal = Goal.KeepWeight
) : State