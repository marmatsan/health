package com.marmatsan.onboarding_domain.di

import com.marmatsan.onboarding_domain.use_case.ValidateGender
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OnboardingDomainModule {
    @Provides
    @ViewModelScoped
    fun provideValidateGenderUseCase(): ValidateGender {
        return ValidateGender()
    }
}