package com.marmatsan.onboarding_domain.usecases

import com.marmatsan.core_domain.util.UiText
import com.marmatsan.core_domain.usecases.UseCaseResult
import com.marmatsan.onboarding_domain.R

class ValidateWeight {
    operator fun invoke(
        weight: String
    ): UseCaseResult<Int> {
        return weight.toIntOrNull()?.let {
            if (it in 25..400) {
                UseCaseResult.Success(data = it)
            } else {
                UseCaseResult.Error(message = UiText.StringResource(R.string.error_height_not_in_range))
            }
        } ?: UseCaseResult.Error(message = UiText.StringResource(R.string.error_age_cant_be_empty))
    }
}