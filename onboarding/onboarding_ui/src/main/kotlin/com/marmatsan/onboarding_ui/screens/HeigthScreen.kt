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
import com.marmatsan.core_ui.dimensions.LocalSpacing
import com.marmatsan.onboarding_domain.R
import com.marmatsan.onboarding_ui.components.ActionButton
import com.marmatsan.onboarding_ui.components.UnitTextField
import com.marmatsan.onboarding_ui.events.HeightEvent
import com.marmatsan.onboarding_ui.events.WeightEvent
import com.marmatsan.onboarding_ui.events.UiEvent
import com.marmatsan.onboarding_ui.states.HeightState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun HeightScreen(
    state: HeightState,
    onEvent: (HeightEvent) -> Unit,
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
            modifier = modifier
                .fillMaxWidth()
                .weight(9f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.whats_your_height)
            )
            Spacer(Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = state.height,
                onValueChange = {
                    onEvent(HeightEvent.OnHeightChange(it))
                },
                unit = stringResource(R.string.cm)
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
                text = stringResource(R.string.back),
                onClick = {
                    onBackClick()
                }
            )
            ActionButton(
                text = stringResource(R.string.next),
                onClick = {
                    onEvent(HeightEvent.OnNextClicked)
                }
            )
        }
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun HeightScreenPreview() {
    HeightScreen(
        state = HeightState(),
        onEvent = { },
        uiEvent = flow { },
        snackbarHostState = SnackbarHostState(),
        onNextClick = { },
        onBackClick = { }
    )
}