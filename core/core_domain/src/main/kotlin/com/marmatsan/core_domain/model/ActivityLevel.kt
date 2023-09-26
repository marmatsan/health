package com.marmatsan.core_domain.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface ActivityLevel {

    enum class ActivityLevelType {
        LOW, MEDIUM, HIGH
    }

    @Serializable
    data object Low : ActivityLevel

    @Serializable
    data object Medium : ActivityLevel

    @Serializable
    data object High : ActivityLevel

    companion object {
        fun fromType(type: ActivityLevelType): ActivityLevel {
            return when (type) {
                ActivityLevelType.LOW -> Low
                ActivityLevelType.MEDIUM -> Medium
                ActivityLevelType.HIGH -> High
            }
        }
    }
}