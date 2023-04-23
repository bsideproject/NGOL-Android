pluginManagement {
    includeBuild("convention-plugin")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://devrepo.kakao.com/nexus/content/groups/public/")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://devrepo.kakao.com/nexus/content/groups/public/")
    }
}
rootProject.name = "nuguollim"
include(
    ":app",
    ":core:data",
    ":core:remote",
    ":core:config",
    ":common:di",
    ":common:design-system",
    ":common:ui",
    ":common:exception",
    ":common:data",
    ":feature:social-login",
    ":feature:search",
    ":core:data-store",
)