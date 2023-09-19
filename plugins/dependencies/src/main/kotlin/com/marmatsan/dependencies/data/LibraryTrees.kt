package com.marmatsan.dependencies.data

import com.marmatsan.dependencies.data.NodeData.Library as Library
import com.marmatsan.dependencies.data.NodeData.ArtifactsGroup as ArtifactsGroup

val androidXLibraryTree = tree(Library(group = "androidx")) {
    tree(
        Library(
            group = "core", artifactsGroups = listOf(
                ArtifactsGroup(
                    name = "core",
                    artifacts = listOf("core-ktx"),
                    version = "1.10.1"
                )
            )
        )
    )
    tree(
        Library(
            group = "datastore", artifactsGroups = listOf(
                ArtifactsGroup(
                    name = "datastore",
                    artifacts = listOf("datastore"),
                    version = "1.0.0"
                )
            )
        )
    )
    tree(
        Library(
            group = "lifecycle", artifactsGroups = listOf(
                ArtifactsGroup(
                    name = "lifecycle",
                    artifacts = listOf("lifecycle-runtime-ktx"),
                    version = "2.6.1"
                )
            )
        )
    )
}

val androidXComposeLibraryTree = tree(Library(group = "androidx")) {
    tree(
        Library(
            group = "activity", artifactsGroups = listOf(
                ArtifactsGroup(
                    name = "activity",
                    artifacts = listOf("activity-compose"),
                    version = "1.7.2"
                )
            )
        )
    )
    tree(
        Library(
            group = "compose"
        )
    ) {
        tree(
            Library(
                group = "animation", artifactsGroups = listOf(
                    ArtifactsGroup(
                        name = "animation",
                        artifacts = listOf("animation"),
                        version = "1.5.0"
                    )
                )
            )
        )
        tree(
            Library(
                group = "compiler", artifactsGroups = listOf(
                    ArtifactsGroup(
                        name = "compiler",
                        artifacts = listOf("compiler"),
                        version = "1.5.0"
                    )
                )
            )
        )
        tree(
            Library(
                group = "foundation", artifactsGroups = listOf(
                    ArtifactsGroup(
                        name = "foundation",
                        artifacts = listOf("foundation"),
                        version = "1.5.0"
                    )
                )
            )
        )
        tree(
            Library(
                group = "material3", artifactsGroups = listOf(
                    ArtifactsGroup(
                        name = "material3",
                        artifacts = listOf("material3"),
                        version = "1.1.1"
                    )
                )
            )
        )
        tree(
            Library(
                group = "runtime", artifactsGroups = listOf(
                    ArtifactsGroup(
                        name = "runtime",
                        artifacts = listOf("runtime"),
                        version = "1.5.0"
                    )
                )
            )
        )
        tree(
            Library(
                group = "ui", artifactsGroups = listOf(
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
    }
    tree(
        Library(
            group = "hilt", artifactsGroups = listOf(
                ArtifactsGroup(
                    name = "hilt",
                    artifacts = listOf("hilt-navigation-compose"),
                    version = "1.0.0"
                )
            )
        )
    )
    tree(
        Library(
            group = "lifecycle", artifactsGroups = listOf(
                ArtifactsGroup(
                    name = "lifecycle",
                    artifacts = listOf("lifecycle-viewmodel-compose"),
                    version = "2.6.1"
                )
            )
        )
    )
    tree(
        Library(
            group = "navigation", artifactsGroups = listOf(
                ArtifactsGroup(
                    name = "navigation",
                    artifacts = listOf("navigation-compose"),
                    version = "2.7.2"
                )
            )
        )
    )
}

val comLibraryTree = tree(Library(group = "com")) {
    tree(Library(group = "google")) {
        tree(
            Library(
                group = "dagger", artifactsGroups = listOf(
                    ArtifactsGroup(
                        name = "dagger",
                        artifacts = listOf("hilt-android", "hilt-android-compiler"),
                        version = "2.48"
                    )
                )
            )
        )
    }
}

val orgLibraryTree = tree(Library(group = "org")) {
    tree(Library(group = "jetbrains")) {
        tree(
            Library(
                group = "kotlinx", artifactsGroups = listOf(
                    ArtifactsGroup(
                        name = "kotlinx",
                        artifacts = listOf("kotlinx-serialization-json"),
                        version = "1.6.0"
                    )
                )
            )
        )
    }
}

val libraryTrees = listOf(androidXLibraryTree, comLibraryTree, orgLibraryTree)