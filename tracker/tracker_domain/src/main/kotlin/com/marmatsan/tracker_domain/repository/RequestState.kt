package com.marmatsan.tracker_domain.repository

import com.marmatsan.core_domain.util.Empty

sealed class RequestState<T>(
    open val data: T? = null,
    open val message: String? = null
) {
    data class Loading<T>(
        val isLoading: Boolean = true
    ) : RequestState<T>()

    data class Success<T>(
        override val data: T? = null
    ) : RequestState<T>(data)

    data class Error<T>(
        override val data: T? = null,
        override val message: String = String.Empty
    ) : RequestState<T>(data, message)
}