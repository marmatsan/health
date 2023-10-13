package com.marmatsan.core_domain.model

sealed interface WeightGoal {
    data object LoseWeight : WeightGoal
    data object KeepWeight : WeightGoal
    data object GainWeight : WeightGoal
}