package com.marmatsan.heal_th.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    private val _startDestination = MutableStateFlow("welcome")
    private val _isLoading = MutableStateFlow(true)

    val startDestination = _startDestination.asStateFlow()
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val onboardingCompleted = preferences.readOnboardingCompleted()
            if (onboardingCompleted) {
                _startDestination.value = "overview"
            } else {
                _startDestination.value = "welcome"
            }
            delay(2000L) // TODO: Make the splash screen more robust
            _isLoading.value = false
        }
    }

}