package com.marmatsan.dependencies

data class NodeData(
    val group: String,
    val version: String? = null,
    val artifacts: List<String>? = null
)