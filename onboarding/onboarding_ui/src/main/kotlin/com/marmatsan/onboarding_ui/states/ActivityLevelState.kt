package com.marmatsan.onboarding_ui.states

import com.marmatsan.core_domain.preferences.model.ActivityLevel
import com.marmatsan.core_ui.state.State

data class ActivityLevelState(
    val activityLevel: ActivityLevel = ActivityLevel.Medium
) : State