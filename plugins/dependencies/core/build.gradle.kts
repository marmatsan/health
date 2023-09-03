plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    val dependencies by plugins.creating {
        id = "com.marmatsan.dependencies"
        implementationClass = "com.marmatsan.dependencies.DependenciesPlugin"
    }
}
