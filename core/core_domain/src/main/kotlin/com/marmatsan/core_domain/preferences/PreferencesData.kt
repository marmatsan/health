package com.marmatsan.core_domain.preferences

import com.marmatsan.core_domain.model.UserData
import kotlinx.serialization.Serializable

@Serializable
data class PreferencesData(
    val userData: UserData = UserData(),
    @Serializable
    val showOnboarding: Boolean = true
)