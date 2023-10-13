package com.marmatsan.heal_th.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marmatsan.onboarding_ui.screens.*
import com.marmatsan.onboarding_ui.viewmodels.*
import com.marmatsan.tracker_ui.screens.overview.OverviewScreen
import com.marmatsan.tracker_ui.viewmodels.OverviewViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = "welcome") {
            WelcomeScreen(
                modifier = androidx.compose.ui.Modifier.padding(paddingValues),
                onNextClick = {
                    navController.navigate("gender")
                }
            )
        }
        composable(route = "gender") {
            val viewModel = hiltViewModel<GenderViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            GenderScreen(
                modifier = androidx.compose.ui.Modifier.padding(paddingValues),
                state = state,
                onEvent = viewModel::onEvent,
                uiEvent = viewModel.uiEvent,
                snackbarHostState = snackbarHostState,
                onNextClick = {
                    navController.navigate("age")
                }
            )
        }
        composable(route = "age") {
            val viewModel = hiltViewModel<AgeViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            AgeScreen(
                modifier = androidx.compose.ui.Modifier.padding(paddingValues),
                state = state,
                onEvent = viewModel::onEvent,
                uiEvent = viewModel.uiEvent,
                snackbarHostState = snackbarHostState,
                onNextClick = {
                    navController.navigate("height")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = "height") {
            val viewModel = hiltViewModel<HeightViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            HeightScreen(
                modifier = androidx.compose.ui.Modifier.padding(paddingValues),
                state = state,
                onEvent = viewModel::onEvent,
                uiEvent = viewModel.uiEvent,
                snackbarHostState = snackbarHostState,
                onNextClick = {
                    navController.navigate("weight")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = "weight") {
            val viewModel = hiltViewModel<WeightViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            WeightScreen(
                modifier = androidx.compose.ui.Modifier.padding(paddingValues),
                state = state,
                onEvent = viewModel::onEvent,
                uiEvent = viewModel.uiEvent,
                snackbarHostState = snackbarHostState,
                onNextClick = {
                    navController.navigate("activity")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = "activity") {
            val viewModel = hiltViewModel<ActivityLevelViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            ActivityLevelScreen(
                modifier = androidx.compose.ui.Modifier.padding(paddingValues),
                state = state,
                onEvent = viewModel::onEvent,
                uiEvent = viewModel.uiEvent,
                snackbarHostState = snackbarHostState,
                onNextClick = {
                    navController.navigate("goal")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = "goal") {
            val viewModel = hiltViewModel<WeightGoalViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            WeightGoalScreen(
                modifier = androidx.compose.ui.Modifier.padding(paddingValues),
                state = state,
                onEvent = viewModel::onEvent,
                uiEvent = viewModel.uiEvent,
                snackbarHostState = snackbarHostState,
                onNextClick = {
                    navController.navigate("nutrient_goal")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = "nutrient_goal") {
            val viewModel = hiltViewModel<NutrientGoalViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            NutrientGoalScreen(
                modifier = androidx.compose.ui.Modifier.padding(paddingValues),
                state = state,
                onEvent = viewModel::onEvent,
                uiEvent = viewModel.uiEvent,
                snackbarHostState = snackbarHostState,
                onNextClick = {
                    navController.navigate("overview")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = "overview") {
            val viewmodel = hiltViewModel<OverviewViewModel>()
            val state by viewmodel.state.collectAsStateWithLifecycle()
            OverviewScreen(
                modifier = androidx.compose.ui.Modifier.padding(paddingValues),
                state = state,
                onEvent = viewmodel::onEvent,
                uiEvent = viewmodel.uiEvent
            )
        }
    }
}