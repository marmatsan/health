package com.marmatsan.tracker_ui.states

import com.marmatsan.core_ui.state.State
import com.marmatsan.tracker_domain.models.TrackedFood
import com.marmatsan.tracker_ui.models.MealItem
import com.marmatsan.tracker_ui.models.defaultMeals
import java.time.LocalDate

data class OverviewFoodsState(
    val date: LocalDate = LocalDate.now(),
    val trackedFoods: List<TrackedFood> = emptyList(),
    val meals: List<MealItem> = defaultMeals
) : State