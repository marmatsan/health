package com.marmatsan.onboarding_ui.states

import com.marmatsan.core_domain.model.WeightGoal
import com.marmatsan.core_ui.state.State

data class WeightGoalState(
    val weightGoal: WeightGoal = WeightGoal.KeepWeight
) : State