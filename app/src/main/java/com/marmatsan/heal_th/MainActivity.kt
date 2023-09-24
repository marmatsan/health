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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marmatsan.heal_th.ui.theme.HealthTheme
import com.marmatsan.onboarding_ui.screens.AgeScreen
import com.marmatsan.onboarding_ui.screens.GenderScreen
import com.marmatsan.onboarding_ui.screens.WelcomeScreen
import com.marmatsan.onboarding_ui.viewmodels.AgeViewModel
import com.marmatsan.onboarding_ui.viewmodels.GenderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthTheme {

                val showOnboarding = true // TODO: Load from datastore
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

                        }
                    }
                }
            }
        }
    }
}