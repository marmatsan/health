package com.marmatsan.onboarding_ui.events

import com.marmatsan.core_domain.model.Gender
import com.marmatsan.core_ui.event.Event

sealed interface GenderEvent : Event {
    data class OnGenderChange(val gender: Gender) : GenderEvent
    data object OnNextClick : GenderEvent
}