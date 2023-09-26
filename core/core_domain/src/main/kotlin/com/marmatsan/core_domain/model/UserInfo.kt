package com.marmatsan.core_domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val gender: Gender? = null,
    val age: Int? = null
)