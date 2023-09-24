package com.marmatsan.onboarding_ui.states

import android.os.Parcelable
import com.marmatsan.core_domain.model.Gender
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenderState(
    val gender: Gender = Gender.Unknown
) : Parcelable