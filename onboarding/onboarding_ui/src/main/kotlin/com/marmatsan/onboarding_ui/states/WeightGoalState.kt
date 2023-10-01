package com.marmatsan.onboarding_ui.states

import com.marmatsan.core_domain.model.Goal
import com.marmatsan.core_ui.state.State

data class WeightGoalState(
    val goal: Goal = Goal.KeepWeight
) : State