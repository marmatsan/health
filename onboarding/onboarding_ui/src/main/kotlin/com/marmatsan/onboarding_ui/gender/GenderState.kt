package com.marmatsan.onboarding_ui.gender

import com.marmatsan.core_domain.model.Gender
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenderState(
    val gender: Gender = Gender.Unknown
)