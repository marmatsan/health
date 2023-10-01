package com.marmatsan.core_data.preferences

import androidx.datastore.core.DataStore
import com.marmatsan.core_domain.model.ActivityLevel
import com.marmatsan.core_domain.model.Gender
import com.marmatsan.core_domain.model.Goal
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.core_domain.preferences.PreferencesData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultPreferences @Inject constructor(
    private val dataStore: DataStore<PreferencesData>
) : Preferences {

    override suspend fun saveGender(gender: Gender) {
        dataStore.updateData {
            it.copy(
                userData = it.userData.copy(
                    gender = gender
                )
            )
        }
    }

    override suspend fun saveAge(age: Int) {
        dataStore.updateData {
            it.copy(
                userData = it.userData.copy(
                    age = age
                )
            )
        }
    }

    override suspend fun saveHeight(height: Int) {
        dataStore.updateData {
            it.copy(
                userData = it.userData.copy(
                    height = height
                )
            )
        }
    }

    override suspend fun saveWeight(weight: Int) {
        dataStore.updateData {
            it.copy(
                userData = it.userData.copy(
                    weight = weight
                )
            )
        }
    }

    override suspend fun saveActivityLevel(activityLevel: ActivityLevel) {
        dataStore.updateData {
            it.copy(
                userData = it.userData.copy(
                    activityLevel = activityLevel
                )
            )
        }
    }

    override suspend fun saveGoal(goal: Goal) {
        dataStore.updateData {
            it.copy(
                userData = it.userData.copy(
                    goal = goal
                )
            )
        }
    }

    override suspend fun saveCarbRatio(carbRatio: Float) {
        dataStore.updateData {
            it.copy(
                userData = it.userData.copy(
                    carbRatio = carbRatio
                )
            )
        }
    }

    override suspend fun saveProteinRatio(proteinRatio: Float) {
        dataStore.updateData {
            it.copy(
                userData = it.userData.copy(
                    proteinRatio = proteinRatio
                )
            )
        }
    }

    override suspend fun saveFatRatio(fatRatio: Float) {
        dataStore.updateData {
            it.copy(
                userData = it.userData.copy(
                    fatRatio = fatRatio
                )
            )
        }
    }

    override suspend fun saveShowOnboarding(showOnboarding: Boolean) {
        dataStore.updateData {
            it.copy(
                showOnboarding = showOnboarding
            )
        }
    }

    override fun preferencesDataFlow(): Flow<PreferencesData> {
        return dataStore.data
    }
}