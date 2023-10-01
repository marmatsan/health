package com.marmatsan.heal_th

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marmatsan.heal_th.ui.theme.HealthTheme
import com.marmatsan.onboarding_ui.screens.*
import com.marmatsan.onboarding_ui.viewmodels.*
import com.marmatsan.tracker_ui.screens.overview.OverviewScreen
import com.marmatsan.tracker_ui.viewmodels.OverviewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition { true }

        setContent {
            HealthTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { paddingValues ->
                    val navController = rememberNavController()
                    NavHost( // TODO: Navigation just for testing (to be improved)
                        navController = navController,
                        startDestination = "welcome"
                    ) {
                        composable(route = "welcome") {
                            WelcomeScreen(
                                modifier = Modifier.padding(paddingValues),
                                onNextClick = {
                                    navController.navigate("gender")
                                }
                            )
                        }
                        composable(route = "gender") {
                            val viewModel = hiltViewModel<GenderViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()
                            GenderScreen(
                                modifier = Modifier.padding(paddingValues),
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
                                modifier = Modifier.padding(paddingValues),
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
                                modifier = Modifier.padding(paddingValues),
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
                                modifier = Modifier.padding(paddingValues),
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
                                modifier = Modifier.padding(paddingValues),
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
                                modifier = Modifier.padding(paddingValues),
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
                                modifier = Modifier.padding(paddingValues),
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
                                modifier = Modifier.padding(paddingValues),
                                state = state,
                                onEvent = viewmodel::onEvent,
                                uiEvent = viewmodel.uiEvent
                            )
                        }
                    }
                }
            }
        }
    }

}