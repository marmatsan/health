package com.marmatsan.tracker_ui.viewmodels

import com.marmatsan.core_domain.usecases.FilterOutDigits
import com.marmatsan.core_ui.viewmodel.BaseViewModel
import com.marmatsan.tracker_domain.usecases.TrackerUseCases
import com.marmatsan.tracker_ui.events.SearchEvent
import com.marmatsan.tracker_ui.states.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val trackerUseCases: TrackerUseCases,
    private val filterOutDigits: FilterOutDigits
) : BaseViewModel<SearchEvent>() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    override suspend fun handleEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnAmountForFoodChange -> TODO()
            is SearchEvent.OnQueryChange -> TODO()
            is SearchEvent.OnSearch -> TODO()
            is SearchEvent.OnSearchFocusChange -> TODO()
            is SearchEvent.OnToggleTrackableFood -> TODO()
            is SearchEvent.OnTrackFoodClick -> TODO()
        }
    }


}