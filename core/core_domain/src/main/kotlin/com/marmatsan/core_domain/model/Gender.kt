package com.marmatsan.core_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
sealed interface Gender : Parcelable {
    enum class GenderType {
        MALE, FEMALE, UNKNOWN
    }

    @Parcelize
    @Serializable
    data object Male : Gender

    @Parcelize
    @Serializable
    data object Female : Gender

    @Parcelize
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