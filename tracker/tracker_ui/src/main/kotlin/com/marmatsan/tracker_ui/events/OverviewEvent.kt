package com.marmatsan.tracker_ui.events

import com.marmatsan.core_ui.event.Event
import com.marmatsan.tracker_domain.models.TrackedFood
import com.marmatsan.tracker_ui.models.MealItem

sealed interface OverviewEvent : Event {
    data class OnToggleMealClick(val mealItem: MealItem) : OverviewEvent
    data class OnAddFoodClick(val mealName: String) : OverviewEvent
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood) : OverviewEvent
    data object OnNextDayClick : OverviewEvent
    data object OnPreviousDayClick : OverviewEvent
}