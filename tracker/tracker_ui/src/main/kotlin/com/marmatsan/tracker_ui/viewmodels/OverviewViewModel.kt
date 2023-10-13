package com.marmatsan.tracker_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.core_ui.viewmodel.BaseViewModel
import com.marmatsan.tracker_domain.usecases.TrackerUseCases
import com.marmatsan.tracker_ui.events.OverviewEvent
import com.marmatsan.tracker_ui.states.NutrientsHeaderState
import com.marmatsan.tracker_ui.states.OverviewFoodsState
import com.marmatsan.tracker_ui.states.OverviewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val preferences: Preferences,
    private val trackerUseCases: TrackerUseCases,
) : BaseViewModel<OverviewEvent>() {

    init {
        viewModelScope.launch {
            preferences.saveOnboardingCompleted(onboardingCompleted = true)
        }
    }

    private val nutrientsHeaderState = MutableStateFlow(NutrientsHeaderState())
    private val overviewFoodsState = MutableStateFlow(OverviewFoodsState())

    val state = combine(
        nutrientsHeaderState,
        overviewFoodsState
    ) { nutrientsHeaderState, overviewFoodsState ->
        OverviewState(
            nutrientsHeaderState = nutrientsHeaderState,
            overviewFoodsState = overviewFoodsState
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), OverviewState())

    override suspend fun handleEvent(event: OverviewEvent) {
        when (event) {
            is OverviewEvent.OnToggleMealClick -> {
                val meals = overviewFoodsState.value.meals

                overviewFoodsState.value = overviewFoodsState.value.copy(
                    meals = meals.map { mealItem ->
                        if (mealItem.name == event.mealItem.name) {
                            mealItem.copy(isExpanded = !mealItem.isExpanded)
                        } else mealItem
                    }
                )
            }

            is OverviewEvent.OnAddFoodClick -> {

            }

            is OverviewEvent.OnDeleteTrackedFoodClick -> {

            }

            is OverviewEvent.OnNextDayClick -> {

            }

            is OverviewEvent.OnPreviousDayClick -> {

            }
        }
    }

}