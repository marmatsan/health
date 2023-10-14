package com.marmatsan.heal_th.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.marmatsan.core_data.preferences.DefaultPreferences
import com.marmatsan.core_data.preferences.PreferencesDataSerializer
import com.marmatsan.core_domain.PreferencesData
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.core_domain.usecases.FilterOutDigits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val DATA_STORE_FILE_NAME = "preferences_data.pb"

    @Singleton
    @Provides
    fun provideProtoDataStore(
        @ApplicationContext appContext: Context
    ): DataStore<PreferencesData> {
        return DataStoreFactory.create(
            serializer = PreferencesDataSerializer,
            produceFile = { appContext.dataStoreFile(DATA_STORE_FILE_NAME) }
        )
    }

    @Singleton
    @Provides
    fun providePreferences(dataStore: DataStore<PreferencesData>): Preferences {
        return DefaultPreferences(dataStore)
    }

    @Singleton
    @Provides
    fun provideFilterOutDigitsUseCase(): FilterOutDigits {
        return FilterOutDigits()
    }

}