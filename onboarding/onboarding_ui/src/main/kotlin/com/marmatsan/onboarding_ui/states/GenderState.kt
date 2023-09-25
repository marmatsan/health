package com.marmatsan.onboarding_ui.states

import com.marmatsan.core_domain.model.Gender

data class GenderState(
    val gender: Gender = Gender.Unknown
) : State()