package com.marmatsan.core_domain.preferences.model

sealed interface ActivityLevel {
    data object Low : ActivityLevel
    data object Medium : ActivityLevel
    data object High : ActivityLevel
}