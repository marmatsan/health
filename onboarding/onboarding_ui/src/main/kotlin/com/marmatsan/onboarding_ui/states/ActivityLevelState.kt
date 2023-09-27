package com.marmatsan.onboarding_ui.states

import com.marmatsan.core_domain.model.ActivityLevel

data class ActivityLevelState(
    val activityLevel: ActivityLevel = ActivityLevel.Medium
) : State