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
import com.marmatsan.core_domain.preferences.model.WeightGoal
import com.marmatsan.onboarding_domain.R
import com.marmatsan.core_ui.dimensions.LocalSpacing
import com.marmatsan.onboarding_ui.components.ActionButton
import com.marmatsan.onboarding_ui.components.SelectableButton
import com.marmatsan.onboarding_ui.events.WeightGoalEvent
import com.marmatsan.core_ui.event.UiEvent
import com.marmatsan.onboarding_ui.states.WeightGoalState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun WeightGoalScreen(
    state: WeightGoalState,
    onEvent: (WeightGoalEvent) -> Unit,
    uiEvent: Flow<UiEvent>,
    snackbarHostState: SnackbarHostState,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val spacing = LocalSpacing.current

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
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .weight(9f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.lose_keep_or_gain_weight)
                )
                Spacer(Modifier.height(spacing.spaceMedium))
                Row {
                    SelectableButton(
                        text = stringResource(id = R.string.lose),
                        isSelected = state.weightGoal is WeightGoal.LoseWeight,
                        onClick = {
                            onEvent(WeightGoalEvent.OnWeightGoalChange(WeightGoal.LoseWeight))
                        }
                    )
                    Spacer(
                        Modifier.width(spacing.spaceMedium)
                    )
                    SelectableButton(
                        text = stringResource(id = R.string.keep),
                        isSelected = state.weightGoal is WeightGoal.KeepWeight,
                        onClick = {
                            onEvent(WeightGoalEvent.OnWeightGoalChange(WeightGoal.KeepWeight))
                        }
                    )
                    Spacer(Modifier.width(spacing.spaceMedium))
                    SelectableButton(
                        text = stringResource(id = R.string.gain),
                        isSelected = state.weightGoal is WeightGoal.GainWeight,
                        onClick = {
                            onEvent(WeightGoalEvent.OnWeightGoalChange(WeightGoal.GainWeight))
                        }
                    )
                }
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
                        onNextClick()
                    }
                )
            }
        }
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun GoalScreenPreview() {
    WeightGoalScreen(
        state = WeightGoalState(),
        onEvent = {},
        uiEvent = flow {},
        snackbarHostState = SnackbarHostState(),
        onNextClick = {},
        onBackClick = {}
    )
}