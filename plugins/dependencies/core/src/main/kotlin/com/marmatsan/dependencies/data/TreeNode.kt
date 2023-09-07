package com.marmatsan.dependencies.data

data class TreeNode(
    val data: NodeData
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
            val nodeData = node.data
            path.add("${nodeData.dependencyId}.")

            if (node.isLeaf()) {
                val joinedPathWithoutLastDot = path.joinToString(separator = "") { it }.removeSuffix(suffix = ".")
                val version = nodeData.version

                // If the node data has artifacts, it is a library. If not, it is a plugin
                nodeData.artifacts?.forEach { artifact ->
                    result.add("$joinedPathWithoutLastDot:${artifact}:$version")
                } ?: result.add("$joinedPathWithoutLastDot:$version")
            } else {
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