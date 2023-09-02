plugins {
    `kotlin-dsl`
    `version-catalog`
    alias(plugins.plugins.com.android.application) apply false
    alias(plugins.plugins.org.jetbrains.kotlin.android) apply false
}

