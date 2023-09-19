package com.marmatsan.onboarding_ui.gender

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.marmatsan.core_domain.model.Gender
import com.marmatsan.core_ui.dimensions.LocalSpacing
import com.marmatsan.onboarding_ui.R
import com.marmatsan.onboarding_ui.components.ActionButton
import com.marmatsan.onboarding_ui.components.SelectableButton

@Composable
fun GenderScreen(
    state: GenderState,
    onEvent: (GenderEvent) -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LocalContext.current
    val spacing = LocalSpacing.current

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
                text = stringResource(id = R.string.whats_your_gender)
            )
            Spacer(modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton(
                    text = stringResource(id = R.string.male),
                    isSelected = state.gender is Gender.Male
                ) {
                    onEvent(GenderEvent.OnGenderChange(Gender.Male))
                }
                Spacer(Modifier.width(spacing.spaceMedium))
                SelectableButton(
                    text = stringResource(id = R.string.female),
                    isSelected = state.gender is Gender.Female
                ) {
                    onEvent(GenderEvent.OnGenderChange(Gender.Female))
                }
            }
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(spacing.spaceMedium)
                .weight(1f),
            horizontalArrangement = Arrangement.End
        ) {
            ActionButton(
                text = stringResource(id = R.string.next),
                onClick = {
                    onNextClick()
                }
            )
        }
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun GenderScreenPreview() {
    GenderScreen(
        state = GenderState(gender = Gender.Male),
        onEvent = { },
        onNextClick = { }
    )
}