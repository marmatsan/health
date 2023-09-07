package com.marmatsan.dependencies.data

class DependenciesHashMap {
    companion object {
        fun generateHashMap(
            dependenciesTree: TreeNode<out NodeData>
        ): MutableMap<String, Dependency> {

            val dependencyMap = mutableMapOf<String, Dependency>()
            val dependencies = TreeNode.findLeafNodePaths(dependenciesTree)

            when (dependenciesTree.data) {
                is NodeData.LibraryData -> { // Tree of libraries

                    dependencies.forEach { dependency ->
                        // dependency has the format dependencyId:artifact:version
                        val parts = dependency.split(":")

                        val libraryGroup = parts[0]
                        val libraryArtifact = parts[1]
                        val libraryVersion = parts[2]

                        val library = dependencyMap.computeIfAbsent(libraryGroup) { _ ->
                            Library(
                                dependencyId = libraryGroup,
                                artifacts = mutableListOf(),
                                version = libraryVersion
                            )
                        } as Library

                        library.artifacts.add(libraryArtifact)

                    }
                }

                is NodeData.PluginData -> { // Tree of plugins

                }
            }

            return dependencyMap
        }
    }
}

abstract class Dependency(
    open val dependencyId: String,
    open val version: String
)

data class Library(
    override val dependencyId: String,
    val artifacts: MutableList<String>,
    override val version: String
) : Dependency(dependencyId, version)

data class Plugin(
    override val dependencyId: String,
    override val version: String
) : Dependency(dependencyId, version)