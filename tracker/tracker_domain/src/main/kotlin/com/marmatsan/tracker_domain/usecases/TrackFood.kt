package com.marmatsan.tracker_domain.usecases

import com.marmatsan.tracker_domain.models.Meal
import com.marmatsan.tracker_domain.models.TrackableFood
import com.marmatsan.tracker_domain.models.TrackedFood
import com.marmatsan.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import kotlin.math.roundToInt

class TrackFood(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(
        trackableFood: TrackableFood,
        amount: Int,
        meal: Meal,
        date: LocalDate
    ) {
        repository.insertTrackedFood(
            TrackedFood(
                name = trackableFood.name,
                carbs = ((trackableFood.carbsPer100g / 100f) * amount).roundToInt(),
                protein = ((trackableFood.proteinPer100g / 100f) * amount).roundToInt(),
                fat = ((trackableFood.fatPer100g / 100f) * amount).roundToInt(),
                calories = ((trackableFood.caloriesPer100g / 100f) * amount).roundToInt(),
                imageUrl = trackableFood.imageUrl,
                meal = meal,
                amount = amount,
                date = date
            )
        )
    }
}