plugins {
    kotlin("jvm")
}

kotlin {
    compilerOptions {
        progressiveMode = true
        extraWarnings =  true

        freeCompilerArgs.add("-Xdata-flow-based-exhaustiveness")
        freeCompilerArgs.add("-Xreturn-value-checker=full")
    }
}

sourceSets {
    main {
        kotlin.setSrcDirs(listOf("src"))
    }
    test {
        kotlin.setSrcDirs(listOf("test"))
    }
}
