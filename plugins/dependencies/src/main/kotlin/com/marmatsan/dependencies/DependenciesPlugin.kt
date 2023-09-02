package com.marmatsan.dependencies

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

        }
    }
}

// Plugin trees
private fun createComTree(): TreeNode<NodeData> {

    val root = TreeNode(
        NodeData(
            group = "com"
        )
    )

    // Unique named nodes data
    val android = NodeData(group = "android") // Duplicate
    val application = NodeData(group = "application")
    val library = NodeData(group = "library")
    val google = NodeData(group = "google")
    val dagger = NodeData(group = "dagger")
    val hilt = NodeData(group = "hilt")
    val devTools = NodeData(group = "devtools")
    val ksp = NodeData(group = "ksp")

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

private fun createOrgTree(): TreeNode<NodeData> {

    val root = TreeNode(
        NodeData(
            group = "org"
        )
    )

    // Unique named nodes data
    val jetbrains = NodeData(group = "jetbrains")
    val kotlin = NodeData(group = "kotlin")
    val android = NodeData(group = "android")
    val jvm = NodeData(group = "jvm")
    val plugin = NodeData(group = "plugin")
    val parcelize = NodeData(group = "parcelize")
    val serialization = NodeData(group = "serialization")

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
    *TreeNode.findLeafNodePaths(createComTree()).toTypedArray(),
    *TreeNode.findLeafNodePaths(createOrgTree()).toTypedArray()
)

// Library trees
