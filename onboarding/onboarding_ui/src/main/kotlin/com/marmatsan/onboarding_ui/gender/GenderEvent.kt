package com.marmatsan.onboarding_ui.gender

import com.marmatsan.core_domain.model.Gender

sealed interface GenderEvent {
    data class OnGenderChange(val gender: Gender) : GenderEvent
    data object OnNextClicked : GenderEvent
}