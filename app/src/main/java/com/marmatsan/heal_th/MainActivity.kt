package com.marmatsan.heal_th

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marmatsan.heal_th.ui.theme.HealthTheme
import com.marmatsan.onboarding_ui.gender.GenderScreen
import com.marmatsan.onboarding_ui.gender.GenderViewModel
import com.marmatsan.onboarding_ui.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthTheme {

                val showOnboarding = true // TODO: Load from datastore

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->
                    val navController = rememberNavController()
                    NavHost(
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
                            GenderScreen(
                                modifier = Modifier.padding(paddingValues),
                                state = viewModel.state,
                                onEvent = viewModel::onEvent,
                                onNextClick = {
                                    // TODO
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}