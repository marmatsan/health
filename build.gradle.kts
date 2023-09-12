plugins {
    alias(plugins.plugins.com.android.application) apply false
    alias(plugins.plugins.org.jetbrains.kotlin.android) apply false

    /* Custom project plugins */
    id("com.marmatsan.compose") apply false
}