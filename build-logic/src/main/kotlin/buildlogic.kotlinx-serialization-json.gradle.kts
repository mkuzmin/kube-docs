plugins {
    kotlin("plugin.serialization")
}

val libs: VersionCatalog = versionCatalogs.named("libs")

dependencies {
    "implementation"(libs.findLibrary("kotlinx-serialization-json").get())
}
