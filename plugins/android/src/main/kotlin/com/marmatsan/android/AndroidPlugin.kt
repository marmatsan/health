package com.marmatsan.android

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

class AndroidPlugin : Plugin<Project> {
    override fun apply(project: Project) {

        val androidExtension = when {
            project.plugins.hasPlugin(AppPlugin::class) -> {
                project.extensions.getByType<ApplicationExtension>()
            }

            else -> {
                project.extensions.getByType<LibraryExtension>()
            }
        }

        androidExtension.apply {
            namespace = "com.marmatsan.${project.name}"
            compileSdk = 34
            defaultConfig {
                minSdk = 26

                if (androidExtension is ApplicationExtension) {
                    androidExtension.apply {
                        defaultConfig {

                            val majorVersion = 1
                            val minorVersion = 0
                            val bugfixVersion = 0

                            targetSdk = 34
                            versionCode = majorVersion * 1000 + minorVersion * 100 + bugfixVersion
                            versionName = "${majorVersion}.${minorVersion}.$bugfixVersion"
                        }
                    }
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }

                project.tasks.withType<KotlinJvmCompile>().configureEach {
                    compilerOptions.languageVersion.set(KotlinVersion.KOTLIN_1_9)
                    compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
                }

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            }

        }
        // Applied plugins
        project.pluginManager.apply("org.jetbrains.kotlin.android")
        project.pluginManager.apply("com.google.dagger.hilt.android")
        project.pluginManager.apply("com.google.devtools.ksp")

        // Applied libs
        val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

        project.dependencies {
            // Dependency injection
            implementation(libs.getLibrary("com.google.dagger.hilt.android"))
            ksp(libs.getLibrary("com.google.dagger.hilt.android.compiler"))
            // Testing
            testImplementation(libs.getLibrary("org.junit.jupiter"))
            testImplementation(libs.getLibrary("com.willowtreeapps.assertk"))
            testImplementation(libs.getLibrary("io.mockk"))
        }

        project.tasks.withType<Test> {
            useJUnitPlatform()
        }
    }

    private fun VersionCatalog.getLibrary(libraryAlias: String): String {
        return findLibrary(libraryAlias).get().get().toString()
    }

    private fun DependencyHandlerScope.implementation(dependencyNotation: String): Dependency? =
        add("implementation", dependencyNotation)

    private fun DependencyHandlerScope.testImplementation(dependencyNotation: String): Dependency? =
        add("testImplementation", dependencyNotation)

    private fun DependencyHandlerScope.ksp(dependencyNotation: String): Dependency? =
        add("ksp", dependencyNotation)
}