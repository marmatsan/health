plugins {
    alias(plugins.plugins.com.android.library)
    alias(plugins.plugins.org.jetbrains.kotlin.plugin.parcelize)
    id("com.marmatsan.android")
    id("com.marmatsan.compose")
}

dependencies {
    /* Modules */
    // Core
    implementation(projects.core.coreUi)
    implementation(projects.core.coreDomain)
    // Onboarding
    implementation(projects.onboarding.onboardingDomain)

    /* Libraries */
    implementation(libs.com.google.protobuf)
}