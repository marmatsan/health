rootProject.name = "plugins"

pluginManagement {
    includeBuild("./android")
    includeBuild("./compose")
    includeBuild("./dependencies")
}

include(
    C
)
