package com.marmatsan.dependencies

sealed class Dependency {
    object Library : Dependency()
    object Plugin : Dependency()
}