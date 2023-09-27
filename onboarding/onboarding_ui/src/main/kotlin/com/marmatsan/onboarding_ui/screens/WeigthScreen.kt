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
import com.marmatsan.onboarding_ui.events.WeightEvent
import com.marmatsan.onboarding_ui.events.UiEvent
import com.marmatsan.onboarding_ui.states.WeightState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun WeightScreen(
    state: WeightState,
    onEvent: (WeightEvent) -> Unit,
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
                text = stringResource(R.string.whats_your_weight)
            )
            Spacer(Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = state.weight,
                onValueChange = {
                    onEvent(WeightEvent.OnWeightChange(it))
                },
                unit = stringResource(R.string.kg)
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
                    onEvent(WeightEvent.OnNextClick)
                }
            )
        }
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun WeightScreenPreview() {
    WeightScreen(
        state = WeightState(),
        onEvent = { },
        uiEvent = flow { },
        snackbarHostState = SnackbarHostState(),
        onNextClick = { },
        onBackClick = { }
    )
}