package com.marmatsan.onboarding_ui.events

import com.marmatsan.core_domain.model.ActivityLevel
import com.marmatsan.core_ui.event.Event

sealed interface ActivityLevelEvent : Event {
    data class OnActivityLevelChange(val activityLevel: ActivityLevel) : ActivityLevelEvent
    data object OnNextClick : ActivityLevelEvent
}