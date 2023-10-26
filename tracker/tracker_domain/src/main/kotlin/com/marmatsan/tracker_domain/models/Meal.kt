package com.marmatsan.tracker_domain.models

sealed interface Meal {

    enum class MealType {
        BREAKFAST,
        LUNCH,
        DINNER,
        SNACK
    }

    data object Breakfast : Meal
    data object Lunch : Meal
    data object Dinner : Meal
    data object Snack : Meal

    companion object {
        fun fromType(mealType: MealType): Meal {
            return when (mealType) {
                MealType.BREAKFAST -> Breakfast
                MealType.LUNCH -> Lunch
                MealType.DINNER -> Dinner
                MealType.SNACK -> Snack
            }
        }

        fun toType(meal: Meal): MealType {
            return when (meal) {
                Breakfast -> MealType.BREAKFAST
                Lunch -> MealType.LUNCH
                Dinner -> MealType.DINNER
                Snack -> MealType.SNACK
            }
        }
    }
}
