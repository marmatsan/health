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