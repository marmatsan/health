package com.marmatsan.core_domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val gender: Gender? = null,
    val age: Int? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val activityLevel: ActivityLevel? = null,
    val goal: Goal? = null,
    val carbRatio: Float? = null,
    val proteinRatio: Float? = null,
    val fatRatio: Float? = null
)