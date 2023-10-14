package com.marmatsan.tracker_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marmatsan.tracker_domain.models.Meal

@Entity
data class TrackedFoodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val imageUrl: String?,
    val mealType: Meal.MealType,
    val amount: Int,
    val day: Int,
    val month: Int,
    val year: Int,
    val calories: Int
)