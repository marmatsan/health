plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins.creating {
        id = "com.marmatsan.dependencies"
        implementationClass = "com.marmatsan.dependencies.DependenciesPlugin"
    }
}
