package com.marmatsan.onboarding_domain.use_case

import com.marmatsan.core_domain.util.UiText

sealed interface UseCaseResult<out T> {
    data class Success<T>(val data: T) : UseCaseResult<T>
    data class Error<T>(val message: UiText) : UseCaseResult<T>
}