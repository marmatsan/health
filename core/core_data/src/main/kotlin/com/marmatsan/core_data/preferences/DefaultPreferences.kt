package com.marmatsan.core_data.preferences

import androidx.datastore.core.DataStore
import com.marmatsan.core_domain.model.Gender
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
                userInfo = it.userInfo.copy(
                    gender = gender
                )
            )
        }
    }

    override suspend fun saveAge(age: Int) {
        dataStore.updateData {
            it.copy(
                userInfo = it.userInfo.copy(
                    age = age
                )
            )
        }
    }

    override suspend fun saveHeight(height: Int) {
        dataStore.updateData {
            it.copy(
                userInfo = it.userInfo.copy(
                    height = height
                )
            )
        }
    }

    override suspend fun saveWeight(weight: Int) {
        dataStore.updateData {
            it.copy(
                userInfo = it.userInfo.copy(
                    weight = weight
                )
            )
        }
    }

    override fun preferencesDataFlow(): Flow<PreferencesData> {
        return dataStore.data
    }
}