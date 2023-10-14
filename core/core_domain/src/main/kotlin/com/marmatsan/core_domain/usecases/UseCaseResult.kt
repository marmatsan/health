package com.marmatsan.core_domain.usecases

import com.marmatsan.core_domain.util.UiText

sealed interface UseCaseResult<out T> {
    data class Success<T>(val data: T) : UseCaseResult<T>
    data class Error(val message: UiText) : UseCaseResult<Nothing>
}