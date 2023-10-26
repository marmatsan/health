package com.marmatsan.tracker_ui.states

import com.marmatsan.core_ui.state.State

data class OverviewState(
    val nutrientsHeaderState: NutrientsHeaderState = NutrientsHeaderState(),
    val overviewFoodsState: OverviewFoodsState = OverviewFoodsState()
) : State