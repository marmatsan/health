package com.marmatsan.core_domain.preferences

import com.marmatsan.core_domain.model.Gender
import kotlinx.coroutines.flow.Flow

interface Preferences {
    // Save data
    suspend fun saveGender(gender: Gender)
    suspend fun saveAge(age: Int)

    // Load data
    fun preferencesDataFlow(): Flow<PreferencesData>
}