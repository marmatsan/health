package com.marmatsan.tracker_ui.events

import com.marmatsan.core_ui.event.Event
import com.marmatsan.tracker_domain.models.Meal
import com.marmatsan.tracker_domain.models.TrackableFood
import java.time.LocalDate

sealed interface SearchEvent : Event {
    data class OnQueryChange(
        val query: String
    ) : SearchEvent

    data object OnSearch : SearchEvent

    data class OnToggleTrackableFood(
        val food: TrackableFood
    ) : SearchEvent

    data class OnAmountForFoodChange(
        val food: TrackableFood,
        val amount: String
    ) : SearchEvent

    data class OnTrackFoodClick(
        val food: TrackableFood,
        val meal: Meal,
        val date: LocalDate
    ) : SearchEvent

    data class OnSearchFocusChange(val isFocused: Boolean) : SearchEvent
}