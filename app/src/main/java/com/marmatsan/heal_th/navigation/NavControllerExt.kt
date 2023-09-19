package com.marmatsan.heal_th.navigation

import androidx.navigation.NavController
import com.marmatsan.core_ui.utils.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    navigate(event.route)
}

fun NavController.navigateBack() {
    popBackStack()
}