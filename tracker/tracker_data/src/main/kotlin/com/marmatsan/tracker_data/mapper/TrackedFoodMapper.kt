package com.marmatsan.tracker_data.mapper

import com.marmatsan.tracker_data.local.entity.TrackedFoodEntity
import com.marmatsan.tracker_domain.models.Meal
import com.marmatsan.tracker_domain.models.TrackedFood
import java.time.LocalDate

fun TrackedFoodEntity.toTrackedFood(): TrackedFood {
    return TrackedFood(
        id = id,
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        meal = Meal.fromType(mealType),
        amount = amount,
        date = LocalDate.of(year, month, day),
        calories = calories
    )
}

fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity {
    return TrackedFoodEntity(
        id = id,
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        mealType = Meal.toType(meal),
        amount = amount,
        day = date.dayOfMonth,
        month = date.monthValue,
        year = date.year,
        calories = calories
    )
}