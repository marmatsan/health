package com.marmatsan.dependencies.plugin

import com.marmatsan.dependencies.data.NodeData
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
        create("plugins") {
            pluginsTrees.forEach { plugin ->
                val parts = plugin.split(":")

                val pluginName = parts.first()
                val pluginVersion = parts.last()

                plugin(
                    pluginName, // Alias
                    pluginName  // Group
                ).version(pluginVersion)
            }
        }
        create("libs") {
            librariesTrees.forEach { library ->

            }
        }
    }
}

// Plugin trees
private fun createPluginsComTree(): TreeNode {

    val root = TreeNode(
        NodeData(
            dependencyId = "com"
        )
    )

    // Unique named nodes data
    val android = NodeData(dependencyId = "android") // Duplicate
    val application = NodeData(dependencyId = "application")
    val library = NodeData(dependencyId = "library")
    val google = NodeData(dependencyId = "google")
    val dagger = NodeData(dependencyId = "dagger")
    val hilt = NodeData(dependencyId = "hilt")
    val devTools = NodeData(dependencyId = "devtools")
    val ksp = NodeData(dependencyId = "ksp")

    // Nodes
    val comAndroidNode = TreeNode(android.copy())
    val applicationNode = TreeNode(application.copy(version = "8.0.2"))
    val libraryNode = TreeNode(library.copy(version = "8.0.2"))
    val googleNode = TreeNode(google)
    val daggerNode = TreeNode(dagger)
    val hiltNode = TreeNode(hilt)
    val hiltAndroidNode = TreeNode(android.copy(version = "2.48"))
    val devToolsNode = TreeNode(devTools)
    val kspNode = TreeNode(ksp.copy(version = "1.9.0-1.0.12"))

    // Create dependencies tree
    root.add(
        comAndroidNode, googleNode
    )
    comAndroidNode.add(
        applicationNode, libraryNode
    )
    googleNode.add(
        daggerNode, devToolsNode
    )
    daggerNode.add(
        hiltNode
    )
    hiltNode.add(
        hiltAndroidNode
    )
    devToolsNode.add(
        kspNode
    )

    return root
}

private fun createPluginsOrgTree(): TreeNode {

    val root = TreeNode(
        NodeData(
            dependencyId = "org"
        )
    )

    // Unique named nodes data
    val jetbrains = NodeData(dependencyId = "jetbrains")
    val kotlin = NodeData(dependencyId = "kotlin")
    val android = NodeData(dependencyId = "android")
    val jvm = NodeData(dependencyId = "jvm")
    val plugin = NodeData(dependencyId = "plugin")
    val parcelize = NodeData(dependencyId = "parcelize")
    val serialization = NodeData(dependencyId = "serialization")

    // Nodes
    val jetbrainsNode = TreeNode(jetbrains)
    val kotlinNode = TreeNode(kotlin)
    val androidNode = TreeNode(android.copy(version = "1.9.0"))
    val jvmNode = TreeNode(jvm.copy(version = "1.9.0"))
    val pluginNode = TreeNode(plugin)
    val parcelizeNode = TreeNode(parcelize.copy(version = "1.9.0"))
    val serializationNode = TreeNode(serialization.copy(version = "1.9.0"))

    // Create dependencies tree
    root.add(
        jetbrainsNode
    )
    jetbrainsNode.add(
        kotlinNode
    )
    kotlinNode.add(
        androidNode, jvmNode, pluginNode
    )
    pluginNode.add(
        parcelizeNode, serializationNode
    )

    return root
}

private val pluginsTrees = listOf(
    *TreeNode.findLeafNodePaths(createPluginsComTree()).toTypedArray(),
    *TreeNode.findLeafNodePaths(createPluginsOrgTree()).toTypedArray()
)

// Library trees

private fun createLibrariesAndroidXTree(): TreeNode {

    val rootNode = TreeNode(
        NodeData(
            dependencyId = "androidx"
        )
    )

    /* Unique Named Nodes */

    val activity = NodeData(dependencyId = "activity")
    val compose = NodeData(dependencyId = "compose")
    val hilt = NodeData(dependencyId = "hilt")
    val lifecycle = NodeData(dependencyId = "lifecycle")
    val navigation = NodeData(dependencyId = "navigation")
    val animation = NodeData(dependencyId = "animation")
    val compiler = NodeData(dependencyId = "compiler")
    val foundation = NodeData(dependencyId = "foundation")
    val material3 = NodeData(dependencyId = "material3")
    val runtime = NodeData(dependencyId = "runtime")
    val ui = NodeData(dependencyId = "ui")

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