package com.marmatsan.tracker_ui.states

import com.marmatsan.core_domain.util.Empty
import com.marmatsan.tracker_ui.screens.search.TrackableFoodUiState

data class SearchState(
    val query: String = String.Empty,
    val isHintVisible: Boolean = true,
    val isSearching: Boolean = false,
    val trackableFoods: List<TrackableFoodUiState> = emptyList()
)