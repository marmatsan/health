package com.marmatsan.dependencies.data

data class TreeNode(
    val nodeData: NodeData
) {
    private val children: MutableList<TreeNode> by lazy {
        mutableListOf()
    }

    fun add(vararg children: TreeNode) = this.children.addAll(children)

    private fun hasChildren(): Boolean = children.size >= 1
    private fun isLeaf(): Boolean = !hasChildren()

    companion object {
        fun findLeafNodePaths(
            node: TreeNode,
            path: MutableList<String> = mutableListOf(),
            result: MutableList<String> = mutableListOf()
        ): MutableList<String> {
            val nodeData = node.nodeData
            path.add(nodeData.dependencyId)

            if (node.isLeaf()) {
                val joinedPath = path.joinToString(separator = "") { it }
                val version = nodeData.version

                nodeData.artifacts?.forEach { artifact ->
                    // Library
                    result.add("$joinedPath:${artifact}:$version")
                } ?:
                // Plugin
                result.add("$joinedPath:$version")

            } else {
                path.add(".")
                for (child in node.children) {
                    findLeafNodePaths(
                        node = child,
                        path = path,
                        result = result
                    )
                }
            }
            path.removeLast()

            return result
        }
    }
}