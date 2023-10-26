package com.marmatsan.onboarding_domain.usecases

import com.marmatsan.core_domain.util.UiText
import com.marmatsan.core_domain.usecases.UseCaseResult
import com.marmatsan.onboarding_domain.R

class ValidateAge {
    operator fun invoke(
        age: String
    ): UseCaseResult<Int> {
        return age.toIntOrNull()?.let {
            if (it in 8..120) {
                UseCaseResult.Success(data = it)
            } else {
                UseCaseResult.Error(message = UiText.StringResource(R.string.error_age_not_in_range))
            }
        } ?: UseCaseResult.Error(message = UiText.StringResource(R.string.error_age_cant_be_empty))
    }
}