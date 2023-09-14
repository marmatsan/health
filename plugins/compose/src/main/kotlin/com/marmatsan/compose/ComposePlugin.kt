package com.marmatsan.compose

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryExtension
import org.gradle.api.NamedDomainObjectProvider
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.catalog.VersionCatalogPlugin
import org.gradle.kotlin.dsl.*

class ComposePlugin : Plugin<Project> {
    override fun apply(project: Project) {

        project.plugins.apply(VersionCatalogPlugin::class.java)

        val androidExtension = when {
            project.plugins.hasPlugin(AppPlugin::class) -> {
                project.extensions.getByType<ApplicationExtension>()
            }

            else -> {
                project.extensions.getByType<LibraryExtension>()
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

        val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libsCompose")

        project.dependencies {
            libs.libraryAliases.forEach { libraryAlias ->
                implementation(libs.getLibrary(libraryAlias))
            }
        }

    }

    private fun VersionCatalog.getLibrary(libraryAlias: String): String {
        return findLibrary(libraryAlias).get().get().toString()
    }

    private fun DependencyHandlerScope.implementation(dependencyNotation: String): Dependency? =
        add("implementation", dependencyNotation)
}