package com.marmatsan.core_domain.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface Gender {
    enum class GenderType {
        MALE, FEMALE, UNKNOWN
    }

    @Serializable
    data object Male : Gender

    @Serializable
    data object Female : Gender

    @Serializable
    data object Unknown : Gender

    companion object {
        fun fromType(type: GenderType): Gender {
            return when (type) {
                GenderType.MALE -> Male
                GenderType.FEMALE -> Female
                GenderType.UNKNOWN -> Unknown
            }
        }
    }
}