plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins.creating {
        id = "com.marmatsan.compose"
        implementationClass = "com.marmatsan.compose.ComposePlugin"
    }
}