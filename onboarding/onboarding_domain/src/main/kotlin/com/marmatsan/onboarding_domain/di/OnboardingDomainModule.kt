package com.marmatsan.onboarding_domain.di

import com.marmatsan.onboarding_domain.use_case.ValidateAge
import com.marmatsan.onboarding_domain.use_case.ValidateGender
import com.marmatsan.onboarding_domain.use_case.ValidateHeight
import com.marmatsan.onboarding_domain.use_case.ValidateWeight
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

    @Provides
    @ViewModelScoped
    fun provideValidateAgeUseCase(): ValidateAge {
        return ValidateAge()
    }

    @Provides
    @ViewModelScoped
    fun provideValidateHeightUseCase(): ValidateHeight {
        return ValidateHeight()
    }

    @Provides
    @ViewModelScoped
    fun provideValidateWeightUseCase(): ValidateWeight {
        return ValidateWeight()
    }
}