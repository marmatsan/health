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
import com.marmatsan.core_domain.model.ActivityLevel
import com.marmatsan.onboarding_domain.R
import com.marmatsan.core_ui.dimensions.LocalSpacing
import com.marmatsan.onboarding_ui.components.ActionButton
import com.marmatsan.onboarding_ui.components.SelectableButton
import com.marmatsan.onboarding_ui.events.ActivityLevelEvent
import com.marmatsan.onboarding_ui.events.UiEvent
import com.marmatsan.onboarding_ui.states.ActivityLevelState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun ActivityLevelScreen(
    state: ActivityLevelState,
    onEvent: (ActivityLevelEvent) -> Unit,
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
                    text = stringResource(id = R.string.whats_your_activity_level)
                )
                Spacer(Modifier.height(spacing.spaceMedium))
                Row {
                    SelectableButton(
                        text = stringResource(id = R.string.low),
                        isSelected = state.activityLevel is ActivityLevel.Low,
                        onClick = {
                            onEvent(ActivityLevelEvent.OnActivityLevelChange(ActivityLevel.Low))
                        }
                    )
                    Spacer(
                        Modifier.width(spacing.spaceMedium)
                    )
                    SelectableButton(
                        text = stringResource(id = R.string.medium),
                        isSelected = state.activityLevel is ActivityLevel.Medium,
                        onClick = {
                            onEvent(ActivityLevelEvent.OnActivityLevelChange(ActivityLevel.Medium))
                        }
                    )
                    Spacer(Modifier.width(spacing.spaceMedium))
                    SelectableButton(
                        text = stringResource(id = R.string.high),
                        isSelected = state.activityLevel is ActivityLevel.High,
                        onClick = {
                            onEvent(ActivityLevelEvent.OnActivityLevelChange(ActivityLevel.High))
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
                        onEvent(ActivityLevelEvent.OnNextClick)
                    }
                )
            }
        }
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun ActivityLevelScreenPreview() {
    ActivityLevelScreen(
        state = ActivityLevelState(),
        onEvent = {},
        uiEvent = flow {},
        snackbarHostState = SnackbarHostState(),
        onNextClick = {},
        onBackClick = {}
    )
}