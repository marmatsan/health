package com.marmatsan.tracker_domain.usecases

import com.marmatsan.core_domain.PreferencesData
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.core_domain.preferences.mapper.toActivityLevel
import com.marmatsan.core_domain.preferences.mapper.toGender
import com.marmatsan.core_domain.preferences.mapper.toWeightGoal
import com.marmatsan.core_domain.preferences.model.ActivityLevel
import com.marmatsan.core_domain.preferences.model.Gender
import com.marmatsan.core_domain.preferences.model.WeightGoal
import com.marmatsan.core_domain.usecases.UseCaseResult
import com.marmatsan.tracker_domain.models.Meal
import com.marmatsan.tracker_domain.models.TrackedFood
import kotlinx.coroutines.flow.first
import kotlin.math.roundToInt

class CalculateMealNutrients(
    private val preferences: Preferences
) {
    suspend operator fun invoke(trackedFoods: List<TrackedFood>): UseCaseResult<Result> {
        val mealNutrientsMap = trackedFoods
            .groupBy { it.meal }
            .mapValues { entry ->
                val meal = entry.key
                val foods = entry.value
                MealNutrients(
                    carbs = foods.sumOf { it.carbs },
                    protein = foods.sumOf { it.protein },
                    fat = foods.sumOf { it.fat },
                    calories = foods.sumOf { it.calories },
                    meal = meal
                )
            }

        val totalCarbs = mealNutrientsMap.values.sumOf { it.carbs }
        val totalProtein = mealNutrientsMap.values.sumOf { it.protein }
        val totalFat = mealNutrientsMap.values.sumOf { it.fat }
        val totalCalories = mealNutrientsMap.values.sumOf { it.calories }

        val preferencesData = preferences.preferencesDataFlow().first()

        val caloricGoal = dailyCaloricRequirement(preferencesData)
        val carbsGoal = (caloricGoal * preferencesData.carbRatio / 4f).roundToInt()
        val proteinGoal = (caloricGoal * preferencesData.proteinRatio / 4f).roundToInt()
        val fatGoal = (caloricGoal * preferencesData.fatRatio / 9f).roundToInt()

        return UseCaseResult.Success(
            data = Result(
                carbsGoal = carbsGoal,
                proteinGoal = proteinGoal,
                fatGoal = fatGoal,
                caloricGoal = caloricGoal,
                totalCarbs = totalCarbs,
                totalProtein = totalProtein,
                totalFat = totalFat,
                totalCalories = totalCalories,
                mealNutrientsMap = mealNutrientsMap
            )
        )
    }

    private fun bmr(preferencesData: PreferencesData): Int {
        val genderProto = preferencesData.gender
        val gender = genderProto.toGender()

        return when (gender) {
            Gender.Female -> {
                (665.09f + 9.56f * preferencesData.weight +
                        1.84f * preferencesData.height - 4.67 * preferencesData.age).roundToInt()
            }

            Gender.Male -> {
                (66.47f + 13.75f * preferencesData.weight +
                        5f * preferencesData.height - 6.75f * preferencesData.age).roundToInt()
            }

            Gender.Unknown -> TODO("At this point, gender should not be unknown")
        }
    }

    private fun dailyCaloricRequirement(preferencesData: PreferencesData): Int {
        val activityLevelProto = preferencesData.activityLevel
        val activityLevel = activityLevelProto.toActivityLevel()

        val activityFactor = when (activityLevel) {
            ActivityLevel.Low -> 1.2f
            ActivityLevel.Medium -> 1.3f
            ActivityLevel.High -> 1.4f
        }

        val weightGoalProto = preferencesData.weightGoal
        val weightGoal = weightGoalProto.toWeightGoal()

        val caloricExtra = when (weightGoal) {
            WeightGoal.GainWeight -> 500
            WeightGoal.KeepWeight -> 0
            WeightGoal.LoseWeight -> -500
        }
        return (bmr(preferencesData) * activityFactor + caloricExtra).roundToInt()
    }

    data class MealNutrients(
        val carbs: Int,
        val protein: Int,
        val fat: Int,
        val calories: Int,
        val meal: Meal
    )

    data class Result(
        val carbsGoal: Int,
        val proteinGoal: Int,
        val fatGoal: Int,
        val caloricGoal: Int,
        val totalCarbs: Int,
        val totalProtein: Int,
        val totalFat: Int,
        val totalCalories: Int,
        val mealNutrientsMap: Map<Meal, MealNutrients>
    )
}