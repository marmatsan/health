package com.marmatsan.onboarding_ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.marmatsan.core_ui.dimensions.LocalSpacing
import com.marmatsan.onboarding_domain.R
import com.marmatsan.onboarding_ui.components.ActionButton

@Composable
fun WelcomeScreen(
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.welcome_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(spacing.spaceLarge))
        ActionButton(
            modifier = modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.lets_go),
            onClick = {
                onNextClick()
            }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(
        onNextClick = {}
    )
}