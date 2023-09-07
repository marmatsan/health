package com.marmatsan.dependencies.data

sealed class NodeData(
    open val dependencyId: String,
    open val version: String? = null
) {
    data class LibraryData(
        override val dependencyId: String,
        val artifacts: List<String>? = null,
        override val version: String? = null
    ) : NodeData(dependencyId, version)

    data class PluginData(
        override val dependencyId: String,
        override val version: String? = null
    ) : NodeData(dependencyId, version)
}