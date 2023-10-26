package com.marmatsan.core_domain.preferences

import com.marmatsan.core_domain.ActivityLevel
import com.marmatsan.core_domain.Gender
import com.marmatsan.core_domain.WeightGoal
import com.marmatsan.core_domain.PreferencesData
import kotlinx.coroutines.flow.Flow

interface Preferences {
    // Save data
    suspend fun saveGender(gender: Gender)
    suspend fun saveAge(age: Int)
    suspend fun saveHeight(height: Int)
    suspend fun saveWeight(weight: Int)
    suspend fun saveActivityLevel(activityLevel: ActivityLevel)
    suspend fun saveWeightGoal(weightGoal: WeightGoal)
    suspend fun saveCarbRatio(carbRatio: Float)
    suspend fun saveProteinRatio(proteinRatio: Float)
    suspend fun saveFatRatio(fatRatio: Float)
    suspend fun saveOnboardingCompleted(onboardingCompleted: Boolean)

    // Load data
    suspend fun readOnboardingCompleted(): Boolean
    fun preferencesDataFlow(): Flow<PreferencesData>
}