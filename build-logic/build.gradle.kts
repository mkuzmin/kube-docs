plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(21) // Kotlin 2.2.20 embedded into Gradle 9.2.1 does not support JVM 25 as a compilation target
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.kotlinx.serialization.gradle.plugin)
}
