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
            val androidXLibraries = TreeNode.getDependencies(androidXLibraryTree())
            createLibraries(androidXLibraries)
            val comLibraries = TreeNode.getDependencies(comLibraryTree())
            createLibraries(comLibraries)
        }
        create("libsCompose") {
            val androidXComposeLibraryTree = TreeNode.getDependencies(androidXComposeLibraryTree())
            createLibraries(androidXComposeLibraryTree)
        }
        create("plugins") {
            val comPlugins = TreeNode.getDependencies(comPluginTree())
            createPlugins(comPlugins)
            val orgPlugins = TreeNode.getDependencies(orgPluginTree())
            createPlugins(orgPlugins)
        }
    }
}

private fun VersionCatalogBuilder.createLibraries(dependencies: List<Dependency>) {
    dependencies.forEach { library -> // TODO: Library has to be always of type Dependency.Library, but it's of type Dependency
        if (library is Dependency.Library) {
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
}

private fun VersionCatalogBuilder.createPlugins(plugins: List<Dependency>) {
    plugins.forEach { plugin -> // TODO: Plugin has to be always of type Dependency.Plugin, but it's of type Dependency
        if (plugin is Dependency.Plugin && plugin.version != null) { // TODO: Plugin version is always not null here
            plugin(
                plugin.id,
                plugin.id
            ).version(plugin.version)
        }
    }
}