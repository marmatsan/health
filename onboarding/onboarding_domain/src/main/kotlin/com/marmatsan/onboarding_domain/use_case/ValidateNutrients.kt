package com.marmatsan.onboarding_domain.use_case

import com.marmatsan.onboarding_domain.R
import com.marmatsan.core_domain.util.UiText

class ValidateNutrients {
    operator fun invoke(
        carbsPctText: String,
        proteinPctText: String,
        fatPctText: String
    ): UseCaseResult<NutrientValues> {
        val carbsPct = carbsPctText.toIntOrNull()
        val proteinPct = proteinPctText.toIntOrNull()
        val fatPct = fatPctText.toIntOrNull()

        if (carbsPct == null || proteinPct == null || fatPct == null) {
            return UseCaseResult.Error(
                message = UiText.StringResource(R.string.error_invalid_values)
            )
        }

        if (carbsPct + proteinPct + fatPct != 100) {
            return UseCaseResult.Error(
                message = UiText.StringResource(R.string.error_not_100_percent)
            )
        }

        return UseCaseResult.Success(
            data = NutrientValues(
                carbsRatio = carbsPct / 100f,
                proteinRatio = proteinPct / 100f,
                fatRatio = fatPct / 100f
            )
        )
    }

    data class NutrientValues(
        val carbsRatio: Float,
        val proteinRatio: Float,
        val fatRatio: Float
    )

}