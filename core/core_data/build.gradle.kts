plugins {
    alias(plugins.plugins.com.android.library)
    id("com.marmatsan.android")
}

dependencies {
    /* Modules */
    implementation(projects.core.coreDomain)

    /* Libraries */
    // DataStore
    implementation(libs.androidx.datastore)
    implementation(libs.org.jetbrains.kotlinx)
}