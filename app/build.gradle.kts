plugins {
    id("com.android.application")
    id("com.marmatsan.android")
    id("com.marmatsan.compose")
}

android {
    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    /* Modules */
    // Core
    implementation(projects.core.coreData)
    implementation(projects.core.coreDomain)
    implementation(projects.core.coreUi)
    // Onboarding
    implementation(projects.onboarding.onboardingDomain)
    implementation(projects.onboarding.onboardingUi)

    /* Libraries */
    implementation(libs.androidx.datastore)
}