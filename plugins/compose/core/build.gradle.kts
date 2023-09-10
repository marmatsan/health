plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `version-catalog`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    // Compose dependencies
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.hilt)
    implementation(libs.androidx.lifecycle.lifecycle.viewmodel.compose)

    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.compiler)
    implementation(libs.androidx.compose.ui.ui)
    implementation(libs.androidx.compose.ui.ui.tooling)
    implementation(libs.androidx.compose.ui.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.ui.geometry)
    implementation(libs.androidx.compose.ui.ui.graphics)
    implementation(libs.androidx.compose.ui.ui.unit)

    compileOnly("com.android.tools.build:gradle:8.0.2")
    implementation(kotlin("gradle-plugin"))
    implementation(kotlin("android-extensions"))
}

gradlePlugin {
    plugins {
        create("com.marmatsan.compose") {
            id = "com.marmatsan.compose"
            implementationClass = "com.marmatsan.compose.ComposePlugin"
        }
    }
}