package com.marmatsan.core_data.preferences

import android.util.Log
import androidx.datastore.core.DataStore
import com.marmatsan.core_domain.ActivityLevel
import com.marmatsan.core_domain.Gender
import com.marmatsan.core_domain.PreferencesData
import com.marmatsan.core_domain.WeightGoal
import com.marmatsan.core_domain.preferences.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.io.IOException
import javax.inject.Inject

class DefaultPreferences @Inject constructor(
    private val dataStore: DataStore<PreferencesData>
) : Preferences {
    override suspend fun saveGender(gender: Gender) {
        dataStore.updateData {
            it.toBuilder().setGender(gender).build()
        }
    }

    override suspend fun saveAge(age: Int) {
        dataStore.updateData {
            it.toBuilder().setAge(age).build()
        }
    }

    override suspend fun saveHeight(height: Int) {
        dataStore.updateData {
            it.toBuilder().setHeight(height).build()
        }
    }

    override suspend fun saveWeight(weight: Int) {
        dataStore.updateData {
            it.toBuilder().setWeight(weight).build()
        }
    }

    override suspend fun saveActivityLevel(activityLevel: ActivityLevel) {
        dataStore.updateData {
            it.toBuilder().setActivityLevel(activityLevel).build()
        }
    }

    override suspend fun saveWeightGoal(weightGoal: WeightGoal) {
        dataStore.updateData {
            it.toBuilder().setWeightGoal(weightGoal).build()
        }
    }

    override suspend fun saveCarbRatio(carbRatio: Float) {
        dataStore.updateData {
            it.toBuilder().setCarbRatio(carbRatio).build()
        }
    }

    override suspend fun saveProteinRatio(proteinRatio: Float) {
        dataStore.updateData {
            it.toBuilder().setProteinRatio(proteinRatio).build()
        }
    }

    override suspend fun saveFatRatio(fatRatio: Float) {
        dataStore.updateData {
            it.toBuilder().setFatRatio(fatRatio).build()
        }
    }

    override suspend fun saveOnboardingCompleted(onboardingCompleted: Boolean) {
        dataStore.updateData {
            it.toBuilder().setOnboardingCompleted(onboardingCompleted).build()
        }
    }

    override fun preferencesDataFlow(): Flow<PreferencesData> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            Log.e("UserPreferencesRepo", "Error reading sort order preferences.", exception)
            emit(PreferencesData.getDefaultInstance())
        } else {
            throw exception
        }
    }

    override suspend fun readOnboardingCompleted(): Boolean {
        return preferencesDataFlow().first().onboardingCompleted
    }

}