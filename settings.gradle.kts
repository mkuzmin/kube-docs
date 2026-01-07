rootProject.name = "kube-docs"

pluginManagement {
    includeBuild("build-logic")
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs", { from(files("libs.versions.toml")) })
    }

    repositories {
        mavenCentral()
    }
}

include("app")
