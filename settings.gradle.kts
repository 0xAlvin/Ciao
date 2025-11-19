pluginManagement {
    repositories {
        google()
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

rootProject.name = "Ciao"
include(":app")
include(":core:common")
include(":core:ui")
include(":core:domain")
include(":core:data")
include(":feature:home")
include(":feature:auth")