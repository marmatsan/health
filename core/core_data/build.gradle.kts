plugins {
    alias(plugins.plugins.com.android.library)
    id("com.marmatsan.android")
}

dependencies {
    /* Modules */
    implementation(projects.core.coreDomain)

    /* Libraries */
    implementation(libs.org.jetbrains.kotlinx)
    implementation(libs.com.google.protobuf)
    implementation(libs.bundles.androidx.datastore.datastore)
}