pluginManagement {
    includeBuild("../dependencies")
}

plugins {
    id("com.marmatsan.dependencies") apply true
}

rootProject.name = "compose"
include(
    ":core"
)
