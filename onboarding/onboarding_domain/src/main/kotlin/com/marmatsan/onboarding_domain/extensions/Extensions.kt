package com.marmatsan.onboarding_domain.extensions

infix fun String.hasAtMostLengthOf(length: Int): Boolean {
    return this.length <= length
}