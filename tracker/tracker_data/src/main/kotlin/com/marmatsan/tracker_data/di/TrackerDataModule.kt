package com.marmatsan.tracker_data.di

import android.content.Context
import androidx.room.Room
import com.marmatsan.tracker_data.local.TrackerDatabase
import com.marmatsan.tracker_data.repository.TrackerRepositoryImpl
import com.marmatsan.tracker_domain.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Singleton
    @Provides
    fun provideTrackerDatabase(
        @ApplicationContext context: Context
    ): TrackerDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = TrackerDatabase::class.java,
            name = "tracker.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideTrackerRepository(
        db: TrackerDatabase,
    ): TrackerRepository {
        return TrackerRepositoryImpl(
            dao = db.dao,
        )
    }

}