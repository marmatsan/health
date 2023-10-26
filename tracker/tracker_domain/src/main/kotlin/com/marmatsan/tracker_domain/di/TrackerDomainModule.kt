package com.marmatsan.tracker_domain.di

import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.tracker_domain.repository.TrackerRepository
import com.marmatsan.tracker_domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {
    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = TrackFood(repository),
            searchFood = SearchFood(repository),
            getFoodsFromCurrentDate = GetFoodsFromCurrentDate(repository),
            deleteTrackedFood = DeleteTrackedFood(),
            calculateMealNutrients = CalculateMealNutrients(preferences)
        )
    }
}