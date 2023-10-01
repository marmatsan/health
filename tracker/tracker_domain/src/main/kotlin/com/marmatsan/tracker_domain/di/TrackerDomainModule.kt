package com.marmatsan.tracker_domain.di

import com.marmatsan.core_domain.preferences.Preferences
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
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = TrackFood(),
            searchFood = SearchFood(),
            getFoodsForDate = GetFoodsForDate(),
            deleteTrackedFood = DeleteTrackedFood(),
            calculateMealNutrients = CalculateMealNutrients()
        )
    }
}