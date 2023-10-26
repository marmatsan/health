plugins {
    alias(plugins.plugins.com.android.library)
    id("com.marmatsan.android")
    alias(plugins.plugins.org.jetbrains.kotlin.plugin.serialization)
}

android {
    buildTypes {
        debug {
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"https://world.openfoodfacts.org\""
            )
        }
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    /* Modules */
    implementation(projects.core.coreDomain)
    implementation(projects.tracker.trackerDomain)

    /* Libraries */
    implementation(libs.bundles.io.ktor.ktor)
    implementation(libs.androidx.room.room.runtime)
    implementation(libs.androidx.room.room.ktx)
    ksp(libs.androidx.room.room.compiler)
}