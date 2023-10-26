package com.marmatsan.onboarding_ui.states

import com.marmatsan.core_domain.preferences.model.Gender
import com.marmatsan.core_ui.state.State

data class GenderState(
    val gender: Gender = Gender.Unknown
) : State