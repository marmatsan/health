package com.marmatsan.core_domain.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface Goal {

    @Serializable
    data object LoseWeight : Goal

    @Serializable
    data object KeepWeight : Goal

    @Serializable
    data object GainWeight : Goal

}