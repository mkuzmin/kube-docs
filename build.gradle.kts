plugins {
    alias(libs.plugins.kotlin) apply false
}

tasks.wrapper {
    gradleVersion = libs.versions.gradle.get()
}
