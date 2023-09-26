package com.marmatsan.onboarding_domain.extensions

infix fun String.hasAtMostALengthOf(length: Int): Boolean {
    return this.length <= length
}