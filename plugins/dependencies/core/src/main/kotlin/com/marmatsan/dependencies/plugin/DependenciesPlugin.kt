package com.marmatsan.dependencies.plugin

import com.marmatsan.dependencies.data.DependenciesHashMap
import com.marmatsan.dependencies.data.NodeData.LibraryData
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
            librariesTrees.forEach { library ->

            }
        }
    }
}

// Library trees

private fun createLibrariesAndroidXTree(): TreeNode<LibraryData> {

    val rootNode = TreeNode(
        LibraryData(
            dependencyId = "androidx"
        )
    )

    /* Unique Named Nodes */

    val activity = LibraryData(dependencyId = "activity")
    val compose = LibraryData(dependencyId = "compose")
    val hilt = LibraryData(dependencyId = "hilt")
    val lifecycle = LibraryData(dependencyId = "lifecycle")
    val navigation = LibraryData(dependencyId = "navigation")
    val animation = LibraryData(dependencyId = "animation")
    val compiler = LibraryData(dependencyId = "compiler")
    val foundation = LibraryData(dependencyId = "foundation")
    val material3 = LibraryData(dependencyId = "material3")
    val runtime = LibraryData(dependencyId = "runtime")
    val ui = LibraryData(dependencyId = "ui")

    /* Duplicates */


    /* Tree Nodes */

    // Level 1
    val activityNode = TreeNode(
        activity.copy(
            version = "1.7.2",
            artifacts = listOf("activity-compose")
        )
    )
    val composeNode = TreeNode(
        compose.copy()
    )
    val hiltNode = TreeNode(
        hilt.copy(
            version = "1.0.0",
            artifacts = listOf("hilt-navigation-compose")
        )
    )
    val lifecycleNode = TreeNode(
        lifecycle.copy(
            version = "2.6.1",
            artifacts = listOf("lifecycle-viewmodel-compose")
        )
    )
    val navigationNode = TreeNode(
        navigation.copy(
            version = "2.6.0",
            artifacts = listOf("navigation-compose")
        )
    )

    // Level 2
    val animationNode = TreeNode(
        animation.copy(
            version = "1.5.0",
            artifacts = listOf("animation")
        )
    )
    val compilerNode = TreeNode(
        compiler.copy(
            version = "1.5.0",
            artifacts = listOf("compiler")
        )
    )
    val foundationNode = TreeNode(
        foundation.copy(
            version = "1.5.0",
            artifacts = listOf("foundation")
        )
    )
    val material3Node = TreeNode(
        material3.copy(
            version = "1.1.1",
            artifacts = listOf("material3")
        )
    )
    val runtimeNode = TreeNode(
        runtime.copy(
            version = "1.5.0",
            artifacts = listOf("runtime")
        )
    )
    val uiNode = TreeNode(
        ui.copy(
            version = "1.5.0",
            artifacts = listOf("ui", "ui-tooling", "ui-geometry", "ui-graphics", "ui-unit")
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

private val librariesTrees = listOf(
    *TreeNode.findLeafNodePaths(createLibrariesAndroidXTree()).toTypedArray()
)