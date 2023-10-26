package com.marmatsan.core_domain.preferences.model

sealed interface WeightGoal {
    data object LoseWeight : WeightGoal
    data object KeepWeight : WeightGoal
    data object GainWeight : WeightGoal
}