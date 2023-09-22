package com.marmatsan.dependencies.plugin

import com.marmatsan.dependencies.data.*
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.api.initialization.dsl.VersionCatalogBuilder
import org.gradle.api.initialization.resolve.DependencyResolutionManagement

class DependenciesPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) {
        settings.dependencyResolutionManagement {
            buildVersionCatalogs()
        }
    }
}

private fun DependencyResolutionManagement.buildVersionCatalogs() {
    versionCatalogs {
        create("libs") {
            libraryTrees.forEach {
                createLibraries(it.getLibraries())
            }
        }
        create("libsCompose") {
            createLibraries(androidXComposeLibraryTree.getLibraries())
        }
        create("plugins") {
            pluginTrees.forEach {
                createPlugins(it.getPlugins())
            }
        }
    }
}

private fun VersionCatalogBuilder.createLibraries(libraries: List<Dependency.Library>) {
    libraries.forEach { library ->
        val libraryGroup = library.group
        val artifactsGroups = library.artifactsGroups

        if (artifactsGroups?.size == 1 && artifactsGroups.first().artifacts.size == 1) {
            val libraryArtifact = artifactsGroups.first().artifacts.first()
            val libraryVersion = artifactsGroups.first().version
            library(
                libraryGroup,
                "$libraryGroup:$libraryArtifact:$libraryVersion"
            )
        } else {
            artifactsGroups?.forEach { artifactsGroup ->
                val bundleName = "$libraryGroup.${artifactsGroup.name}"
                version(
                    bundleName,
                    artifactsGroup.version
                )
                val artifactsAliases = mutableListOf<String>()
                artifactsGroup.artifacts.forEach { artifact ->
                    val artifactAlias = "$libraryGroup.$artifact"
                    library(
                        artifactAlias,
                        libraryGroup,
                        artifact
                    ).versionRef(bundleName)
                    artifactsAliases.add(artifactAlias)
                }
                bundle(
                    bundleName,
                    artifactsAliases
                )
            }
        }
    }
}

private fun VersionCatalogBuilder.createPlugins(plugins: List<Dependency.Plugin>) {
    plugins.forEach { plugin ->
        if (plugin.version != null) // TODO: Plugin version is always not null here
            plugin(
                plugin.id,
                plugin.id
            ).version(plugin.version)
    }
}