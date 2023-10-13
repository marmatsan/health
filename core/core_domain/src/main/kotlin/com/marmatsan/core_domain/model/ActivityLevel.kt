package com.marmatsan.core_domain.model

sealed interface ActivityLevel {
    data object Low : ActivityLevel
    data object Medium : ActivityLevel
    data object High : ActivityLevel
}