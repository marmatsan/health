package com.marmatsan.tracker_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.core_domain.usecases.UseCaseResult
import com.marmatsan.core_ui.viewmodel.BaseViewModel
import com.marmatsan.tracker_domain.usecases.TrackerUseCases
import com.marmatsan.tracker_ui.events.OverviewEvent
import com.marmatsan.tracker_ui.states.NutrientsHeaderState
import com.marmatsan.tracker_ui.states.OverviewFoodsState
import com.marmatsan.tracker_ui.states.OverviewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val preferences: Preferences,
    private val trackerUseCases: TrackerUseCases,
) : BaseViewModel<OverviewEvent>() {

    private var getFoodsFromCurrentDate: Job? = null

    private val nutrientsHeaderState = MutableStateFlow(NutrientsHeaderState())
    private val overviewFoodsState = MutableStateFlow(OverviewFoodsState())

    init {
        refreshFoods()
        viewModelScope.launch {
            preferences.saveOnboardingCompleted(onboardingCompleted = true)
        }
    }

    val state = combine(
        nutrientsHeaderState,
        overviewFoodsState
    ) { nutrientsHeaderState, overviewFoodsState ->
        OverviewState(
            nutrientsHeaderState = nutrientsHeaderState,
            overviewFoodsState = overviewFoodsState
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = OverviewState()
    )

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

            is OverviewEvent.OnDeleteTrackedFoodClick -> {

            }

            is OverviewEvent.OnNextDayClick -> {
                overviewFoodsState.value = overviewFoodsState.value.copy(
                    date = overviewFoodsState.value.date.plusDays(1)
                )
                refreshFoods()
            }

            is OverviewEvent.OnPreviousDayClick -> {
                overviewFoodsState.value = overviewFoodsState.value.copy(
                    date = overviewFoodsState.value.date.minusDays(1)
                )
                refreshFoods()
            }
        }
    }

    private fun refreshFoods() {
        getFoodsFromCurrentDate?.cancel()
        getFoodsFromCurrentDate = trackerUseCases
            .getFoodsFromCurrentDate(overviewFoodsState.value.date)
            .onEach { trackedFoods ->
                when (val nutrientsResult = trackerUseCases.calculateMealNutrients(trackedFoods)) {
                    is UseCaseResult.Error -> TODO()
                    is UseCaseResult.Success -> {
                        nutrientsHeaderState.value = nutrientsHeaderState.value.copy(
                            totalCarbs = nutrientsResult.data.totalCarbs,
                            totalProtein = nutrientsResult.data.totalProtein,
                            totalFat = nutrientsResult.data.totalFat,
                            totalCalories = nutrientsResult.data.totalCalories,
                            carbsGoal = nutrientsResult.data.carbsGoal,
                            proteinGoal = nutrientsResult.data.proteinGoal,
                            fatGoal = nutrientsResult.data.fatGoal,
                            caloricGoal = nutrientsResult.data.caloricGoal,
                        )
                        overviewFoodsState.value = overviewFoodsState.value.copy(
                            trackedFoods = trackedFoods,
                            meals = overviewFoodsState.value.meals.map { mealItem ->
                                val nutrientsForMeal =
                                    nutrientsResult.data.mealNutrientsMap[mealItem.meal]
                                        ?: return@map mealItem.copy(
                                            carbs = 0,
                                            protein = 0,
                                            fat = 0,
                                            calories = 0
                                        )
                                mealItem.copy(
                                    carbs = nutrientsForMeal.carbs,
                                    protein = nutrientsForMeal.protein,
                                    fat = nutrientsForMeal.fat,
                                    calories = nutrientsForMeal.calories
                                )
                            }
                        )
                    }
                }
            }
            .launchIn(viewModelScope)
    }

}