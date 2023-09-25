package com.marmatsan.core_domain.preferences

import com.marmatsan.core_domain.model.Gender
import kotlinx.coroutines.flow.Flow

interface Preferences<T : Any> {
    // Save data
    suspend fun saveData(value : T)

    // Load data
    fun preferencesDataFlow(): Flow<PreferencesData>
}