package com.marmatsan.tracker_ui.states

import com.marmatsan.core_ui.state.State

data class NutrientsHeaderState(
    val totalCarbs: Int = 0,
    val totalProtein: Int = 0,
    val totalFat: Int = 0,
    val totalCalories: Int = 0,
    val carbsGoal: Int = 0,
    val proteinGoal: Int = 0,
    val fatGoal: Int = 0,
    val caloricGoal: Int = 0
) : State