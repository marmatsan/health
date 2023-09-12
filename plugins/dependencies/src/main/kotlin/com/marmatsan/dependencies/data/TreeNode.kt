package com.marmatsan.dependencies.data

class TreeNode<T : NodeData>(
    val data: T
) {
    private val children: MutableList<TreeNode<T>> by lazy {
        mutableListOf()
    }

    fun add(vararg children: TreeNode<T>) = this.children.addAll(children)

    private fun hasChildren(): Boolean = children.size >= 1
    private fun isLeaf(): Boolean = !hasChildren()

    companion object {
        fun getDependencies(
            node: TreeNode<out NodeData>,
            path: MutableList<String> = mutableListOf(),
            result: MutableList<Dependency> = mutableListOf()
        ): List<Dependency> {
            val nodeData = node.data
            path.add("${nodeData.id}.")

            if (node.isLeaf()) {
                val joinedPathWithoutLastDot = path.joinToString(separator = "") { it }.removeSuffix(suffix = ".")
                when (nodeData) {
                    is NodeData.Library -> {
                        result.add(
                            Dependency.Library(
                                group = joinedPathWithoutLastDot,
                                artifactsGroups = nodeData.artifactsGroups?.map { artifactsGroup ->
                                    Dependency.ArtifactsGroup(
                                        name = artifactsGroup.name,
                                        artifacts = artifactsGroup.artifacts,
                                        version = artifactsGroup.version
                                    )
                                }
                            )
                        )
                    }

                    is NodeData.Plugin -> {
                        result.add(
                            Dependency.Plugin(
                                id = joinedPathWithoutLastDot,
                                version = nodeData.version
                            )
                        )
                    }
                }
            } else {
                node.children.forEach { child ->
                    getDependencies(
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