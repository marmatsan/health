plugins {
    alias(plugins.plugins.com.android.library)
    id("com.marmatsan.android")
}

dependencies {
    /* Modules */
    implementation(projects.core.coreDomain)

    /* Libraries */
    implementation(libs.androidx.datastore)
    implementation(libs.org.jetbrains.kotlinx)
}