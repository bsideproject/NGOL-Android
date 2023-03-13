pluginManagement {
    includeBuild("convention-plugin")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "nuguollim"
include(
    ":app",
    ":core:data",
    ":core:remote",
    ":common:di",
    ":common:design-system",
    ":common:ui"
    ":feature:social-login",
)