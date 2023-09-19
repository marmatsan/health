package com.marmatsan.core_domain.preferences

import com.marmatsan.core_domain.model.Gender
import kotlinx.coroutines.flow.Flow

interface Preferences {
    // Save data
    suspend fun saveGender(gender: Gender)

    // Load data
    fun loadPreferencesData(): Flow<PreferencesData>
}