package com.marmatsan.dependencies.data

import com.marmatsan.dependencies.data.NodeData.Plugin as Plugin

val comPluginTree = tree(Plugin(id = "com")) {
    tree(Plugin(id = "android")) {
        tree(Plugin(id = "application", version = "8.1.1"))
        tree(Plugin(id = "library", version = "8.1.1"))
    }
    tree(Plugin(id = "google")) {
        tree(Plugin(id = "devtools")) {
            tree(Plugin(id = "ksp", version = "1.9.10-1.0.13"))
        }
        tree(Plugin(id = "dagger")) {
            tree(Plugin(id = "hilt")) {
                tree(Plugin(id = "android", version = "2.48"))
            }
        }
    }
}


val orgPluginTree = tree(Plugin(id = "org")) {
    tree(Plugin(id = "jetbrains")) {
        tree(Plugin(id = "kotlin")) {
            tree(Plugin(id = "android", version = "1.9.10"))
        }
    }
}

val pluginTrees = listOf(comPluginTree, orgPluginTree)