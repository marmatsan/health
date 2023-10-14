package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.model.ActivityLevel
import com.marmatsan.core_domain.ActivityLevel as ProtoActivityLevel
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.core_ui.viewmodel.BaseViewModel
import com.marmatsan.onboarding_ui.events.ActivityLevelEvent
import com.marmatsan.onboarding_ui.states.ActivityLevelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityLevelViewModel @Inject constructor(
    private val preferences: Preferences
) : BaseViewModel<ActivityLevelEvent>() {

    private val _state = MutableStateFlow(ActivityLevelState())
    val state = _state.asStateFlow()

    override suspend fun handleEvent(event: ActivityLevelEvent) {
        when (event) {
            is ActivityLevelEvent.OnActivityLevelChange -> {
                _state.value = _state.value.copy(activityLevel = event.activityLevel)
            }

            is ActivityLevelEvent.OnNextClick -> {
                viewModelScope.launch {
                    when (state.value.activityLevel) {
                        ActivityLevel.High -> preferences.saveActivityLevel(ProtoActivityLevel.HIGH)
                        ActivityLevel.Medium -> preferences.saveActivityLevel(ProtoActivityLevel.MEDIUM)
                        ActivityLevel.Low -> preferences.saveActivityLevel(ProtoActivityLevel.LOW)
                    }
                    sendSuccessEvent()
                }
            }
        }
    }
}