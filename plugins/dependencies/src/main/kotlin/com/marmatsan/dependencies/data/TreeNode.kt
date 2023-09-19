package com.marmatsan.dependencies.data

class TreeNode<T : NodeData>(
    private val data: T
) {
    private val children: MutableList<TreeNode<T>> by lazy {
        mutableListOf()
    }

    private fun hasChildren(): Boolean = children.size >= 1
    private fun isLeaf(): Boolean = !hasChildren()

    fun add(child: TreeNode<T>) = this.children.add(child)

    fun getDependencies(
        path: MutableList<String> = mutableListOf(),
        result: MutableList<Dependency> = mutableListOf()
    ): List<Dependency> {
        val nodeData = this.data
        path.add("${nodeData.id}.")

        if (this.isLeaf()) {
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
            this.children.forEach { child ->
                child.getDependencies(
                    path = path,
                    result = result
                )
            }
        }
        path.removeLast()

        return result
    }
}