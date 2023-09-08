package com.marmatsan.dependencies.plugin

import com.marmatsan.dependencies.data.Dependency
import com.marmatsan.dependencies.data.NodeData.Library as Library
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
                            val bundleName = "$libraryGroup.bundle.${artifactsGroup.name}"
                            version(
                                bundleName,
                                artifactsGroup.version
                            )
                            artifactsGroup.artifacts.forEach { artifact ->
                                library(
                                    artifact,
                                    libraryGroup,
                                    artifact
                                ).versionRef(bundleName)
                            }
                            bundle(
                                bundleName,
                                artifactsGroup.artifacts
                            )
                        }
                    }

                }
            }
        }
    }
}

// Library trees

private fun androidXLibraryTree(): TreeNode<Library> {

    val rootNode = TreeNode(
        Library(
            group = "androidx"
        )
    )

    /* Unique Named Nodes */

    val activity = Library(group = "activity")
    val compose = Library(group = "compose")
    val hilt = Library(group = "hilt")
    val lifecycle = Library(group = "lifecycle")
    val navigation = Library(group = "navigation")
    val animation = Library(group = "animation")
    val compiler = Library(group = "compiler")
    val foundation = Library(group = "foundation")
    val material3 = Library(group = "material3")
    val runtime = Library(group = "runtime")
    val ui = Library(group = "ui")

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
                    artifacts = listOf("lifecycle-viewmodel-compose"),
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
                    artifacts = listOf("ui", "ui-tooling", "ui-geometry", "ui-graphics", "ui-unit"),
                    version = "1.5.0"
                )
            )
        )
    )

    /* Create tree */

    // Level 1
    rootNode.add(
        activityNode, composeNode, hiltNode, lifecycleNode, navigationNode
    )
    // Level 2
    composeNode.add(
        animationNode, compilerNode, foundationNode, material3Node, runtimeNode, uiNode
    )

    return rootNode
}

// Plugins trees
