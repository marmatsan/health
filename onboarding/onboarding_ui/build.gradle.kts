plugins {
    alias(plugins.plugins.com.android.library)
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
}