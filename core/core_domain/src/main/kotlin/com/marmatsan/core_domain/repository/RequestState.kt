package com.marmatsan.core_domain.repository

sealed interface RequestState<T> {
    data class Loading<T>(val isLoading: Boolean = true) : RequestState<T>
    data class Success<T>(val data: T?) : RequestState<T>
    data class Error<T>(val message: String) : RequestState<T>
}