package com.marmatsan.compose

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.tasks.getExternalNativeLibs
import com.android.build.gradle.internal.tasks.getProjectNativeLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.dependencies

class ComposePlugin : Plugin<Project> {
    override fun apply(project: Project) {

        val androidExtension = when {
            project.plugins.hasPlugin(AppPlugin::class.java) -> {
                project.extensions.getByName("android") as ApplicationExtension
            }

            else -> {
                project.extensions.getByName("android") as LibraryExtension
            }
        }

        androidExtension.apply {

            defaultConfig {
                vectorDrawables {
                    useSupportLibrary = true
                }
            }

            buildFeatures {
                compose = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = "1.5.0"
            }

        }

        project.dependencies {
            implementation()
        }

    }

    fun DependencyHandler.implementation(dependencyNotation: String): Dependency? =
        add("implementation", dependencyNotation)
}
