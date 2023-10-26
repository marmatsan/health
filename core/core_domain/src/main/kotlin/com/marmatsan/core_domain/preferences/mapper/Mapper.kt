package com.marmatsan.core_domain.preferences.mapper

import com.marmatsan.core_domain.preferences.model.ActivityLevel
import com.marmatsan.core_domain.ActivityLevel as ActivityLevelProto
import com.marmatsan.core_domain.preferences.model.WeightGoal
import com.marmatsan.core_domain.WeightGoal as WeightGoalProto
import com.marmatsan.core_domain.preferences.model.Gender
import com.marmatsan.core_domain.Gender as GenderProto


fun ActivityLevelProto.toActivityLevel(): ActivityLevel {
    return when (this) {
        ActivityLevelProto.LOW -> ActivityLevel.Low
        ActivityLevelProto.MEDIUM -> ActivityLevel.Medium
        ActivityLevelProto.HIGH -> ActivityLevel.High
        ActivityLevelProto.UNRECOGNIZED -> throw KotlinNullPointerException()
    }
}

fun WeightGoalProto.toWeightGoal(): WeightGoal {
    return when (this) {
        WeightGoalProto.LOSE_WEIGHT -> WeightGoal.LoseWeight
        WeightGoalProto.KEEP_WEIGHT -> WeightGoal.KeepWeight
        WeightGoalProto.GAIN_WEIGHT -> WeightGoal.GainWeight
        WeightGoalProto.UNRECOGNIZED -> throw KotlinNullPointerException()
    }
}

fun GenderProto.toGender(): Gender {
    return when (this) {
        GenderProto.FEMALE -> Gender.Female
        GenderProto.MALE -> Gender.Male
        GenderProto.UNKNOWN -> Gender.Unknown
        GenderProto.UNRECOGNIZED -> throw KotlinNullPointerException()
    }
}

/*
fun ActivityLevel.toActivityLevelProto(): ActivityLevelProto {
    return when (this) {
        ActivityLevel.High -> ActivityLevelProto.HIGH
        ActivityLevel.Medium -> ActivityLevelProto.MEDIUM
        ActivityLevel.Low -> ActivityLevelProto.LOW
    }
}

fun WeightGoal.toWeightGoalProto(): WeightGoalProto {
    return when (this) {
        WeightGoal.GainWeight -> WeightGoalProto.GAIN_WEIGHT
        WeightGoal.KeepWeight -> WeightGoalProto.KEEP_WEIGHT
        WeightGoal.LoseWeight -> WeightGoalProto.LOSE_WEIGHT
    }
}

fun Gender.toGenderProto(): GenderProto {
    return when (this) {
        Gender.Female -> GenderProto.FEMALE
        Gender.Male -> GenderProto.MALE
        Gender.Unknown -> GenderProto.UNKNOWN
    }
}
 */

