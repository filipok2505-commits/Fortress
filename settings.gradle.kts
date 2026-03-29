pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Fortress"
val coreDir = "core"
val featureDir = "feature"



addFeatureApiImplModule("vault")
addFeatureApiImplModule("auth")
addFeatureApiImplModule("autofill")
addCoreModule("common")
addCoreModule("ui")
addCoreModule("crypto")
addCoreModule("security")
addCoreModule("sync")
addCoreModule("database")
addCoreModule("navigation")

fun addCoreModule(moduleName: String) {
    includeModule(moduleName, coreDir)
}

fun addFeatureApiImplModule(moduleName: String) {
    includeApiImpl(moduleName, featureDir)
}

fun includeApiImpl(
    name: String,
    rootDirectory: String,
) {
    includeModule(name, rootDirectory)
    includeModule(name, rootDirectory)
}

fun includeModule(
    name: String,
    rootDirectory: String,
) {
    val moduleName = ":$rootDirectory:$name"
    val modulePath = listOfNotNull(rootDirectory, name).joinToString(separator = "/")
    include(moduleName)
    project(moduleName).projectDir = File(modulePath)
}

include(":app")
include(":core")
include(":feature")


include(":feature:vault")
include(":feature:auth")
include(":feature:autofill")
include(":core:common")
include(":core:crypto")
include(":core:database")
include(":core:security")
include(":core:sync")
include(":core:ui")
include(":core:navigation")
