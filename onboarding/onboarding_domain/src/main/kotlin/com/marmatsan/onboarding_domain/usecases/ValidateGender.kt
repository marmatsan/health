package com.marmatsan.onboarding_domain.usecases

import com.marmatsan.core_domain.preferences.model.Gender
import com.marmatsan.core_domain.util.UiText
import com.marmatsan.core_domain.usecases.UseCaseResult
import com.marmatsan.onboarding_domain.R

class ValidateGender {
    operator fun invoke(
        gender: Gender
    ): UseCaseResult<Gender> {
        return if (gender is Gender.Unknown) {
            UseCaseResult.Error(message = UiText.StringResource(R.string.error_select_gender))
        } else {
            UseCaseResult.Success(gender)
        }
    }
}