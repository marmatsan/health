package com.marmatsan.tracker_ui.screens.search

import com.marmatsan.core_domain.util.Empty
import com.marmatsan.tracker_domain.models.TrackableFood

data class TrackableFoodUiState(
    val trackableFood: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = String.Empty
)