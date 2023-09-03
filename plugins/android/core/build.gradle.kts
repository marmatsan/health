plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    val android by plugins.creating {
        id = "com.marmatsan.android"
        implementationClass = "com.marmatsan.android.AndroidPlugin"
    }
}
