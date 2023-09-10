package com.marmatsan.dependencies.plugin

import com.marmatsan.dependencies.data.Dependency
import com.marmatsan.dependencies.data.NodeData.Library as LibraryNodeData
import com.marmatsan.dependencies.data.NodeData.Plugin as PluginNodeData

import com.marmatsan.dependencies.data.NodeData.ArtifactsGroup as ArtifactsGroup
import com.marmatsan.dependencies.data.TreeNode
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
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

            androidXLibraries.forEach { library -> // TODO: Library has to be always of type Dependency.Library, but it's of type Dependency
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
        create("plugins") {
            val comPlugins = TreeNode.getDependencies(comPluginTree())

            comPlugins.forEach { plugin -> // TODO: Plugin has to be always of type Dependency.Plugin, but it's of type Dependency
                if (plugin is Dependency.Plugin && plugin.version != null) { // TODO: Plugin version is always not null here
                    plugin(
                        plugin.id,
                        plugin.id
                    ).version(plugin.version)
                }
            }

            val orgPlugins = TreeNode.getDependencies(orgPluginTree())

            orgPlugins.forEach { plugin -> // TODO: Improve repetition of code
                if (plugin is Dependency.Plugin && plugin.version != null) {
                    plugin(
                        plugin.id,
                        plugin.id
                    ).version(plugin.version)
                }
            }
        }
    }
}

// Library trees

private fun androidXLibraryTree(): TreeNode<LibraryNodeData> {

    val rootNode = TreeNode(
        LibraryNodeData(
            group = "androidx"
        )
    )

    /* Unique Named Nodes */

    val activity = LibraryNodeData(group = "activity")
    val compose = LibraryNodeData(group = "compose")
    val core = LibraryNodeData(group = "core")
    val hilt = LibraryNodeData(group = "hilt")
    val lifecycle = LibraryNodeData(group = "lifecycle")
    val navigation = LibraryNodeData(group = "navigation")
    val animation = LibraryNodeData(group = "animation")
    val compiler = LibraryNodeData(group = "compiler")
    val foundation = LibraryNodeData(group = "foundation")
    val material3 = LibraryNodeData(group = "material3")
    val runtime = LibraryNodeData(group = "runtime")
    val ui = LibraryNodeData(group = "ui")

    /* Duplicates */


    /* Tree Nodes */

    // Level 1
    val activityNode = TreeNode(
        activity.copy(
            artifactsGroups = listOf(
                ArtifactsGroup(
                    name = "activity",
                    artifacts = listOf("activity-compose"),
                    version = "1.7.2"
                )
            )
        )
    )

    val composeNode = TreeNode(
        compose.copy()
    )

    val coreNode = TreeNode(
        core.copy(
            artifactsGroups = listOf(
                ArtifactsGroup(
                    name = "core",
                    artifacts = listOf("core-ktx"),
                    version = "1.10.1"
                )
            )
        )
    )

    val hiltNode = TreeNode(
        hilt.copy(
            artifactsGroups = listOf(
                ArtifactsGroup(
                    name = "hilt",
                    artifacts = listOf("hilt-navigation-compose"),
                    version = "1.0.0"
                )
            )
        )
    )

        val lifecycleNode = TreeNode(
        lifecycle.copy(
            artifactsGroups = listOf(
                ArtifactsGroup(
                    name = "lifecycle",
                    artifacts = listOf("lifecycle-viewmodel-compose", "lifecycle-runtime-ktx"),
                    version = "2.6.1"
                )
            )
        )
    )

    val navigationNode = TreeNode(
        navigation.copy(
            artifactsGroups = listOf(
                ArtifactsGroup(
                    name = "navigation",
                    artifacts = listOf("navigation-compose"),
                    version = "2.6.0"
                )
            )
        )
    )

    // Level 2
    val animationNode = TreeNode(
        animation.copy(
            artifactsGroups = listOf(
                ArtifactsGroup(
                    name = "animation",
                    artifacts = listOf("animation"),
                    version = "1.5.0"
                )
            )
        )
    )
    val compilerNode = TreeNode(
        compiler.copy(
            artifactsGroups = listOf(
                ArtifactsGroup(
                    name = "compiler",
                    artifacts = listOf("compiler"),
                    version = "1.0.0"
                )
            )
        )
    )
    val foundationNode = TreeNode(
        foundation.copy(
            artifactsGroups = listOf(
                ArtifactsGroup(
                    name = "foundation",
                    artifacts = listOf("foundation"),
                    version = "1.5.0"
                )
            )
        )
    )
    val material3Node = TreeNode(
        material3.copy(
            artifactsGroups = listOf(
                ArtifactsGroup(
                    name = "material3",
                    artifacts = listOf("material3"),
                    version = "1.1.1"
                )
            )
        )
    )
    val runtimeNode = TreeNode(
        runtime.copy(
            artifactsGroups = listOf(
                ArtifactsGroup(
                    name = "runtime",
                    artifacts = listOf("runtime"),
                    version = "1.5.0"
                )
            )
        )
    )
    val uiNode = TreeNode(
        ui.copy(
            artifactsGroups = listOf(
                ArtifactsGroup(
                    name = "ui",
                    artifacts = listOf(
                        "ui",
                        "ui-tooling",
                        "ui-tooling-preview",
                        "ui-geometry",
                        "ui-graphics",
                        "ui-unit"
                    ),
                    version = "1.5.0"
                )
            )
        )
    )

    /* Create tree */

    // Level 1
    rootNode.add(
        activityNode, composeNode, coreNode, hiltNode, lifecycleNode, navigationNode
    )
    // Level 2
    composeNode.add(
        animationNode, compilerNode, foundationNode, material3Node, runtimeNode, uiNode
    )

    return rootNode
}

// Plugins trees

private fun comPluginTree(): TreeNode<PluginNodeData> {

    val rootNode = TreeNode(
        PluginNodeData(
            id = "com"
        )
    )

    /* Unique Named Nodes */

    val android = PluginNodeData(id = "android")
    val application = PluginNodeData(id = "application")

    /* Duplicates */


    /* Tree Nodes */

    // Level 1
    val androidNode = TreeNode(
        android.copy()
    )

    // Level 2
    val applicationNode = TreeNode(
        application.copy(
            version = "8.1.0-rc01"
        )
    )

    /* Create tree */

    // Level 1
    rootNode.add(
        androidNode
    )
    // Level 2
    androidNode.add(
        applicationNode
    )

    return rootNode
}

private fun orgPluginTree(): TreeNode<PluginNodeData> {

    val rootNode = TreeNode(
        PluginNodeData(
            id = "org"
        )
    )

    /* Unique Named Nodes */

    val jetbrains = PluginNodeData(id = "jetbrains")
    val kotlin = PluginNodeData(id = "kotlin")
    val android = PluginNodeData(id = "android")

    /* Duplicates */


    /* Tree Nodes */

    // Level 1
    val jetbrainsNode = TreeNode(
        jetbrains.copy()
    )

    // Level 2
    val kotlinNode = TreeNode(
        kotlin.copy()
    )

    // Level 3
    val androidNode = TreeNode(
        android.copy(
            version = "1.9.0"
        )
    )

    /* Create tree */

    // Level 1
    rootNode.add(
        jetbrainsNode
    )
    // Level 2
    jetbrainsNode.add(
        kotlinNode
    )

    // Level 3
    kotlinNode.add(
        androidNode
    )

    return rootNode
}