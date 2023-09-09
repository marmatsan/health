plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `version-catalog`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("com.marmatsan.dependencies") {
            id = "com.marmatsan.dependencies"
            implementationClass = "com.marmatsan.dependencies.plugin.DependenciesPlugin"
        }
    }
}
