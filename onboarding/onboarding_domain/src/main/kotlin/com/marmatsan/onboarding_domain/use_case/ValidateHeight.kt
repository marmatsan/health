package com.marmatsan.onboarding_domain.use_case

import com.marmatsan.core_domain.util.UiText
import com.marmatsan.onboarding_domain.R

class ValidateHeight {
    operator fun invoke(
        height: String
    ): UseCaseResult<Int> {
        return height.toIntOrNull()?.let {
            if (it in 100..240) {
                UseCaseResult.Success(data = it)
            } else {
                UseCaseResult.Error(message = UiText.StringResource(R.string.error_height_not_in_range))
            }
        } ?: UseCaseResult.Error(message = UiText.StringResource(R.string.error_age_cant_be_empty))
    }
}