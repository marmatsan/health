plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.marmatsan.compose")
}

android {
    namespace = "com.marmatsan.heal_th"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.marmatsan.heal_th"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = "19"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

}