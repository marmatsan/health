plugins {
    alias(plugins.plugins.com.android.library)
    alias(plugins.plugins.org.jetbrains.kotlin.plugin.serialization)
    alias(plugins.plugins.org.jetbrains.kotlin.plugin.parcelize)
    alias(plugins.plugins.com.google.protobuf)
    id("com.marmatsan.android")
}

dependencies {
    /* Libraries */
    implementation(libs.com.google.protobuf)
    implementation(libs.org.jetbrains.kotlinx)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.24.3"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}