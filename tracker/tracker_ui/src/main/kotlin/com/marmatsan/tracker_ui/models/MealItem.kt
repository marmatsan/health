package com.marmatsan.tracker_ui.models

import androidx.annotation.DrawableRes
import com.marmatsan.core_domain.util.UiText
import com.marmatsan.tracker_domain.models.Meal
import com.marmatsan.tracker_domain.R
import com.marmatsan.tracker_ui.R as uiR

data class MealItem(
    val name: UiText,
    @DrawableRes
    val drawableRes: Int,
    val meal: Meal,
    val carbs: Int = 0,
    val protein: Int = 0,
    val fat: Int = 0,
    val calories: Int = 0,
    val isExpanded: Boolean = false
)

val defaultMeals = listOf(
    MealItem(
        name = UiText.StringResource(R.string.breakfast),
        drawableRes = uiR.drawable.ic_breakfast,
        meal = Meal.Breakfast
    ),
    MealItem(
        name = UiText.StringResource(R.string.lunch),
        drawableRes = uiR.drawable.ic_lunch,
        meal = Meal.Lunch
    ),
    MealItem(
        name = UiText.StringResource(R.string.dinner),
        drawableRes = uiR.drawable.ic_dinner,
        meal = Meal.Dinner
    ),
    MealItem(
        name = UiText.StringResource(R.string.snacks),
        drawableRes = uiR.drawable.ic_snack,
        meal = Meal.Snack
    ),
)