package com.marmatsan.tracker_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.usecases.FilterOutDigits
import com.marmatsan.core_domain.util.UiText
import com.marmatsan.core_ui.event.UiEvent
import com.marmatsan.core_ui.viewmodel.BaseViewModel
import com.marmatsan.tracker_domain.repository.RequestState
import com.marmatsan.tracker_domain.usecases.TrackerUseCases
import com.marmatsan.tracker_domain.R
import com.marmatsan.tracker_ui.events.SearchEvent
import com.marmatsan.tracker_ui.screens.search.TrackableFoodUiState
import com.marmatsan.tracker_ui.states.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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

            is SearchEvent.OnQueryChange -> {
                _state.value = _state.value.copy(query = event.query)
            }

            is SearchEvent.OnSearch -> executeSearch()

            is SearchEvent.OnSearchFocusChange -> {
                _state.value = _state.value.copy(
                    isHintVisible = !event.isFocused && _state.value.query.isBlank()
                )
            }

            is SearchEvent.OnToggleTrackableFood -> TODO()
            is SearchEvent.OnTrackFoodClick -> TODO()
        }
    }

    private fun executeSearch() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isSearching = true,
                trackableFoods = emptyList()
            )
            trackerUseCases.searchFood(state.value.query).collect { request ->
                when (request) {
                    is RequestState.Error -> {
                        _uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.StringResource(R.string.error_something_went_wrong)
                            )
                        )
                    }

                    is RequestState.Loading -> {
                        _state.value = _state.value.copy(
                            isSearching = true
                        )
                    }

                    is RequestState.Success -> {
                        _state.value = _state.value.copy(
                            query = "",
                            trackableFoods = request.data?.map {
                                TrackableFoodUiState(it)
                            } ?: emptyList(),
                            isSearching = false
                        )
                    }
                }
            }
        }
    }

}