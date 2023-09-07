package com.marmatsan.dependencies.data

class TreeNode<T>(
    val data: T
) {
    private val children: MutableList<TreeNode<T>> by lazy {
        mutableListOf()
    }

    fun add(vararg children: TreeNode<T>) = this.children.addAll(children)

    private fun hasChildren(): Boolean = children.size >= 1
    private fun isLeaf(): Boolean = !hasChildren()

    companion object {
        fun findLeafNodePaths(
            node: TreeNode<out NodeData>,
            path: MutableList<String> = mutableListOf(),
            result: MutableList<String> = mutableListOf()
        ): List<String> {
            val nodeData = node.data
            path.add("${nodeData.dependencyId}.")

            if (node.isLeaf()) {
                val joinedPathWithoutLastDot = path.joinToString(separator = "") { it }.removeSuffix(suffix = ".")
                val version = nodeData.version
                when (nodeData) {
                    is NodeData.LibraryData -> {
                        nodeData.artifacts?.forEach { artifact ->
                            result.add("$joinedPathWithoutLastDot:${artifact}:$version")
                        }
                    }

                    is NodeData.PluginData -> {
                        result.add("$joinedPathWithoutLastDot:$version")
                    }
                }
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