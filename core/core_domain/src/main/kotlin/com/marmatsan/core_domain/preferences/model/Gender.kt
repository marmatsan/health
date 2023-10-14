package com.marmatsan.core_domain.preferences.model

sealed interface Gender {
    enum class GenderType {
        MALE, FEMALE, UNKNOWN
    }
    data object Male : Gender
    data object Female : Gender
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