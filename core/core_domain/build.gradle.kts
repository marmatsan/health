plugins {
    alias(plugins.plugins.com.android.library)
    alias(plugins.plugins.org.jetbrains.kotlin.plugin.serialization)
    alias(plugins.plugins.org.jetbrains.kotlin.plugin.parcelize)
    id("com.marmatsan.android")
}

dependencies {
    /* Libraries */
    implementation(libs.org.jetbrains.kotlinx)
}