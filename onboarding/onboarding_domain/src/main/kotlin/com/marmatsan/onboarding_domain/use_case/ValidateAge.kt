package com.marmatsan.onboarding_domain.use_case

import com.marmatsan.core_domain.util.UiText
import com.marmatsan.onboarding_domain.R

class ValidateAge {
    operator fun invoke(
        age: String
    ): UseCaseResult<Int> {
        return age.toIntOrNull()?.let {
            UseCaseResult.Success(data = it)
        } ?: UseCaseResult.Error(message = UiText.StringResource(R.string.error_age_cant_be_empty))
    }
}