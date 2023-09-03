package com.marmatsan.dependencies

data class TreeNode<T>(
    val value: T
) {
    private val children: MutableList<TreeNode<T>> by lazy {
        mutableListOf()
    }

    fun add(child: TreeNode<T>) = children.add(child)
    fun add(vararg children: TreeNode<T>) = this.children.addAll(children)

    private fun hasChildren(): Boolean = children.size >= 1
    private fun isLeaf(): Boolean = !hasChildren()

    companion object {
        fun findLeafNodePaths(
            node: TreeNode<NodeData>,
            path: MutableList<String> = mutableListOf(),
            result: MutableList<String> = mutableListOf()
        ): MutableList<String> {

            path.add(node.value.group)
            if (node.isLeaf()) {
                result.add("${path.joinToString(separator = "") { it }}:${node.value.version}")
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