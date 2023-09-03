package com.marmatsan.dependencies

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.api.initialization.resolve.DependencyResolutionManagement
import org.w3c.dom.Node

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
private fun createPluginsComTree(): TreeNode<NodeData> {

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

private fun createPluginsOrgTree(): TreeNode<NodeData> {

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
    *TreeNode.findLeafNodePaths(createPluginsComTree()).toTypedArray(),
    *TreeNode.findLeafNodePaths(createPluginsOrgTree()).toTypedArray()
)

// Library trees
private fun createLibrariesAndroidXTree(): TreeNode<NodeData> {

    val root = TreeNode(
        NodeData(
            group = "androidx"
        )
    )

    // Unique named nodes data
    val activity = NodeData(group = "activity")
    val animation = NodeData(group = "animation")
    val compiler = NodeData(group = "compiler")
    val compose = NodeData(group = "compose")
    val core = NodeData(group = "core")
    val datastore = NodeData(group = "datastore")
    val foundation = NodeData(group = "foundation")
    val hilt = NodeData(group = "hilt")
    val ktx = NodeData(group = "ktx")
    val lifecycle = NodeData(group = "lifecycle")
    val material3 = NodeData(group = "material3")
    val navigation = NodeData(group = "navigation")
    val room = NodeData(group = "root")
    val runtime = NodeData(group = "runtime")
    val splashScreen = NodeData(group = "splashscreen")
    val ui = NodeData(group = "ui")

    // Nodes
    val activityNode = TreeNode(
        activity.copy(
            artifacts = listOf("activity-compose"),
            version = "1.7.2"
        )
    )
    val animationNode = TreeNode(
        animation.copy(
            artifacts = listOf("animation"),
            version = "1.5.0"
        )
    )
    val composeNode = TreeNode(
        compose
    )
    val foundationNode = TreeNode(
        foundation.copy(
            artifacts = listOf("foundation"),
            version = "1.5.0"
        )
    )
    val material3Node = TreeNode(
        material3.copy(
            artifacts = listOf("material3"),
            version = "1.1.1"
        )
    )
    val runtimeNode = TreeNode(
        runtime.copy(
            artifacts = listOf("runtime"),
            version = "1.5.0"
        )
    )
    val compilerNode = TreeNode(
        compiler.copy(
            artifacts = listOf("compiler"),
            version = "1.5.0"
        )
    )
    val navigationNode = TreeNode(
        navigation.copy(
            artifacts = listOf("navigation-compose"),
            version = "2.6.0"
        )
    )
    val hiltNode = TreeNode(
        hilt.copy(
            artifacts = listOf("hilt-navigation-compose"),
            version = "1.0.0"
        )
    )
    val lifecycleNode = TreeNode(
        lifecycle.copy(
            artifacts = listOf("lifecycle-viewmodel-compose"),
            version = "2.6.1"
        )
    )
    val uiNode = TreeNode(
        ui.copy(
            artifacts = listOf(
                "ui",
                "ui-tooling",
                "ui-geometry",
                "ui-graphics",
                "ui-unit"
            ),
            version = "1.5.0"
        )
    )

    // Create dependencies tree
    root.add(
        composeNode, navigationNode, hiltNode, activityNode, lifecycleNode
    )
    composeNode.add(
        animationNode, foundationNode, material3Node, runtimeNode, compilerNode, uiNode
    )

    return root
}

private fun createLibrariesComTree(): TreeNode<NodeData> {

    val root = TreeNode(
        NodeData(
            group = "com"
        )
    )

    // Unique named nodes data
    val google = NodeData(group = "google")
    val dagger = NodeData(group = "dagger")


    // Nodes

    return root
}

private fun createLibrariesOrgTree(): TreeNode<NodeData> {

    val root = TreeNode(
        NodeData(
            group = "com"
        )
    )

    // Unique named nodes data


    return root
}