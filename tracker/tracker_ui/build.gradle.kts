plugins {
    alias(plugins.plugins.com.android.library)
    id("com.marmatsan.android")
    id("com.marmatsan.compose")
}

dependencies {
    /* Modules */
    implementation(projects.core.coreUi)
    implementation(projects.core.coreDomain)
    implementation(projects.tracker.trackerDomain)

    /* Libraries */
    // Coil
    implementation(libs.io.coil.kt)
}