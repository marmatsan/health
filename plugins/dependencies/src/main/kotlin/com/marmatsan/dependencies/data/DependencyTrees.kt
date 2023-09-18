package com.marmatsan.dependencies.data


// Library trees

fun androidXLibraryTree(): TreeNode<NodeData.Library> {

    val rootNode = TreeNode(
        NodeData.Library(
            group = "androidx"
        )
    )

    //region Unique Named Nodes

    val core = NodeData.Library(group = "core")
    val lifecycle = NodeData.Library(group = "lifecycle")

    //endregion Unique Named Nodes

    //region Tree Nodes

    //region Level 1
    val coreNode = TreeNode(
        core.copy(
            artifactsGroups = listOf(
                NodeData.ArtifactsGroup(
                    name = "core",
                    artifacts = listOf("core-ktx"),
                    version = "1.10.1"
                )
            )
        )
    )

    val lifecycleNode = TreeNode(
        lifecycle.copy(
            artifactsGroups = listOf(
                NodeData.ArtifactsGroup(
                    name = "lifecycle",
                    artifacts = listOf("lifecycle-runtime-ktx"),
                    version = "2.6.1"
                )
            )
        )
    )
    //endregion Level 1

    //endregion Tree Nodes

    //region Create tree

    // Level 1
    rootNode.add(
        coreNode, lifecycleNode
    )

    //endregion Create tree

    return rootNode
}

fun androidXComposeLibraryTree(): TreeNode<NodeData.Library> {

    val rootNode = TreeNode(
        NodeData.Library(
            group = "androidx"
        )
    )

    //region Unique Named Nodes

    val activity = NodeData.Library(group = "activity")
    val compose = NodeData.Library(group = "compose")
    val hilt = NodeData.Library(group = "hilt")
    val lifecycle = NodeData.Library(group = "lifecycle")
    val navigation = NodeData.Library(group = "navigation")
    val animation = NodeData.Library(group = "animation")
    val compiler = NodeData.Library(group = "compiler")
    val foundation = NodeData.Library(group = "foundation")
    val material3 = NodeData.Library(group = "material3")
    val runtime = NodeData.Library(group = "runtime")
    val ui = NodeData.Library(group = "ui")

    //endregion Unique Named Nodes

    //region Tree Nodes

    //region Level 1
    val activityNode = TreeNode(
        activity.copy(
            artifactsGroups = listOf(
                NodeData.ArtifactsGroup(
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
                NodeData.ArtifactsGroup(
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
                NodeData.ArtifactsGroup(
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
                NodeData.ArtifactsGroup(
                    name = "navigation",
                    artifacts = listOf("navigation-compose"),
                    version = "2.6.0"
                )
            )
        )
    )

    //endregion Level 1

    //region Level 2
    val animationNode = TreeNode(
        animation.copy(
            artifactsGroups = listOf(
                NodeData.ArtifactsGroup(
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
                NodeData.ArtifactsGroup(
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
                NodeData.ArtifactsGroup(
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
                NodeData.ArtifactsGroup(
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
                NodeData.ArtifactsGroup(
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
                NodeData.ArtifactsGroup(
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
    //endregion Level 2

    //endregion Tree Nodes

    //region Create tree

    rootNode.add(
        activityNode, composeNode, hiltNode, lifecycleNode, navigationNode
    )

    composeNode.add(
        animationNode, compilerNode, foundationNode, material3Node, runtimeNode, uiNode
    )

    //endregion Create tree
    return rootNode
}

fun comLibraryTree(): TreeNode<NodeData.Library> {

    val rootNode = TreeNode(
        NodeData.Library(
            group = "com"
        )
    )

    //region Unique Named Nodes


    val google = NodeData.Library(group = "google")
    val dagger = NodeData.Library(group = "dagger")

    //endregion Unique Named Nodes

    //region Tree Nodes

    //region Level 1

    val googleNode = TreeNode(
        google.copy()
    )

    //endregion Level 1

    //region Level 2

    val daggerNode = TreeNode(
        dagger.copy(
            artifactsGroups = listOf(
                NodeData.ArtifactsGroup(
                    name = "dagger",
                    artifacts = listOf("hilt-android", "hilt-android-compiler"),
                    version = "2.48"
                )
            )
        )
    )

    //region Level 2

    //endregion Tree Nodes

    //region Create tree

    rootNode.add(
        googleNode
    )

    googleNode.add(
        daggerNode
    )

    //endregion Create tree

    return rootNode
}

// Plugins trees

fun comPluginTree(): TreeNode<NodeData.Plugin> {

    val rootNode = TreeNode(
        NodeData.Plugin(
            id = "com"
        )
    )

    /* Unique Named Nodes */

    val android = NodeData.Plugin(id = "android")
    val google = NodeData.Plugin(id = "google")
    val dagger = NodeData.Plugin(id = "dagger")
    val hilt = NodeData.Plugin(id = "hilt")
    val application = NodeData.Plugin(id = "application")
    val marmatsan = NodeData.Plugin(id = "marmatsan")
    val compose = NodeData.Plugin(id = "compose")
    val dependencies = NodeData.Plugin(id = "dependencies")
    val devtools = NodeData.Plugin(id = "devtools")
    val ksp = NodeData.Plugin(id = "ksp")
    val library = NodeData.Plugin(id = "library")

    /* Duplicates */

    val androidHilt = android.copy()

    /* Tree Nodes */

    // Level 1
    val androidNode = TreeNode(
        android.copy()
    )
    val googleNode = TreeNode(
        google.copy()
    )
    val marmatsanNode = TreeNode(
        marmatsan.copy()
    )

    // Level 2
    val applicationNode = TreeNode(
        application.copy(
            version = "8.1.1"
        )
    )
    val libraryNode = TreeNode(
        library.copy(
            version = "8.1.1"
        )
    )
    val composeNode = TreeNode(
        compose.copy()
    )
    val dependenciesNode = TreeNode(
        dependencies.copy()
    )

    val devtoolsNode = TreeNode(
        devtools.copy()
    )

    val daggerNode = TreeNode(
        dagger.copy()
    )

    // Level 3
    val kspNode = TreeNode(
        ksp.copy(
            version = "1.9.10-1.0.13"
        )
    )

    val hiltNode = TreeNode(
        hilt.copy()
    )

    // Level 4
    val androidHiltNode = TreeNode(
        androidHilt.copy(
            version = "2.48"
        )
    )

    /* Create tree */

    // Level 1
    rootNode.add(
        androidNode, googleNode, marmatsanNode
    )

    // Level 2
    androidNode.add(
        applicationNode, libraryNode
    )

    googleNode.add(
        devtoolsNode, daggerNode
    )

    marmatsanNode.add(
        composeNode, dependenciesNode
    )

    // Level 3
    devtoolsNode.add(
        kspNode
    )

    daggerNode.add(
        hiltNode
    )

    // Level 4
    hiltNode.add(
        androidHiltNode
    )

    return rootNode
}

fun orgPluginTree(): TreeNode<NodeData.Plugin> {

    val rootNode = TreeNode(
        NodeData.Plugin(
            id = "org"
        )
    )

    /* Unique Named Nodes */

    val jetbrains = NodeData.Plugin(id = "jetbrains")
    val kotlin = NodeData.Plugin(id = "kotlin")
    val android = NodeData.Plugin(id = "android")

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
            version = "1.9.10"
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