package com.marmatsan.dependencies.data

data class NodeData(
    val dependencyId: String,
    val version: String? = null,
    val artifacts: List<String>? = null
)