package com.marmatsan.tracker_ui.screens.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import com.marmatsan.core_ui.dimensions.LocalSpacing
import com.marmatsan.core_ui.event.UiEvent
import com.marmatsan.tracker_domain.R
import com.marmatsan.tracker_ui.events.OverviewEvent
import com.marmatsan.tracker_ui.screens.overview.components.*
import com.marmatsan.tracker_ui.states.OverviewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate

@OptIn(ExperimentalCoilApi::class)
@Composable
fun OverviewScreen(
    state: OverviewState,
    onEvent: (OverviewEvent) -> Unit,
    uiEvent: Flow<UiEvent>,
    navigateToSearch: (String, LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {}
                is UiEvent.Success -> {}
            }
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            NutrientsHeader(
                state = state.nutrientsHeaderState
            )
            Spacer(Modifier.height(spacing.spaceMedium))
            DaySelector(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = spacing.spaceMedium
                    ),
                date = state.overviewFoodsState.date,
                onNextDayClick = {
                    onEvent(OverviewEvent.OnNextDayClick)
                },
                onPreviousDayClick = {
                    onEvent(OverviewEvent.OnPreviousDayClick)
                },
            )
            Spacer(Modifier.height(spacing.spaceMedium))
        }
        items(state.overviewFoodsState.meals) { mealItem ->
            ExpandableMeal(
                modifier = Modifier.fillMaxWidth(),
                mealItem = mealItem,
                onToggleClick = {
                    onEvent(OverviewEvent.OnToggleMealClick(mealItem))
                }
            ) {
                // Tracked food list for the selected meal type (breakfast, lunch, etc)
                Column(
                    modifier = Modifier.padding(horizontal = spacing.spaceSmall)
                ) {
                    state.overviewFoodsState.trackedFoods.filter {
                        it.meal == mealItem.meal
                    }.forEach { trackedFood ->
                        TrackedFoodItem(
                            trackedFood = trackedFood,
                            onDeleteClick = {
                                onEvent(OverviewEvent.OnDeleteTrackedFoodClick(trackedFood))
                            }
                        )
                        Spacer(Modifier.height(spacing.spaceMedium))
                    }
                    AddButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(
                            id = R.string.add_meal,
                            mealItem.name.asString(context).lowercase()
                        ),
                        onClick = {
                            navigateToSearch(
                                mealItem.name.asString(context),
                                state.overviewFoodsState.date
                            )
                        }
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun OverviewScreenPreview() {
    OverviewScreen(
        modifier = Modifier,
        state = OverviewState(),
        onEvent = {},
        uiEvent = flow { },
        navigateToSearch = { _, _ -> }
    )
}