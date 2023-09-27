package com.marmatsan.onboarding_ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.marmatsan.onboarding_domain.R
import com.marmatsan.core_ui.dimensions.LocalSpacing
import com.marmatsan.onboarding_ui.components.ActionButton
import com.marmatsan.onboarding_ui.components.UnitTextField
import com.marmatsan.onboarding_ui.events.NutrientGoalEvent
import com.marmatsan.onboarding_ui.events.UiEvent
import com.marmatsan.onboarding_ui.states.NutrientGoalState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun NutrientGoalScreen(
    state: NutrientGoalState,
    onEvent: (NutrientGoalEvent) -> Unit,
    uiEvent: Flow<UiEvent>,
    snackbarHostState: SnackbarHostState,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                is UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .weight(9f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.what_are_your_nutrient_goals)
            )
            Spacer(modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = state.carbsPct,
                onValueChange = {
                    onEvent(NutrientGoalEvent.OnCarbPctChange(it))
                },
                unit = stringResource(id = R.string.percent_carbs)
            )
            Spacer(modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = state.proteinPct,
                onValueChange = {
                    onEvent(NutrientGoalEvent.OnProteinPctChange(it))
                },
                unit = stringResource(id = R.string.percent_proteins)
            )
            Spacer(modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = state.fatPct,
                onValueChange = {
                    onEvent(NutrientGoalEvent.OnFatPctChange(it))
                },
                unit = stringResource(id = R.string.percent_fats)
            )
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(spacing.spaceMedium)
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ActionButton(
                text = stringResource(id = R.string.back),
                onClick = {
                    onBackClick()
                }
            )
            ActionButton(
                text = stringResource(id = R.string.next),
                onClick = {
                    onEvent(NutrientGoalEvent.OnNextClick)
                }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun NutrientGoalScreenPreview() {
    NutrientGoalScreen(
        state = NutrientGoalState(),
        onEvent = {},
        uiEvent = flow {},
        snackbarHostState = SnackbarHostState(),
        onNextClick = {},
        onBackClick = {}
    )
}