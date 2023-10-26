plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    compileOnly("com.android.tools.build:gradle:8.1.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
    implementation("org.jetbrains.kotlin:kotlin-android-extensions:1.9.10")
}

gradlePlugin {
    plugins.register("com.marmatsan.compose") {
        id = "com.marmatsan.compose"
        implementationClass = "com.marmatsan.compose.ComposePlugin"
    }
}