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
    ":core:repository",
    ":common:di",
    ":common:design-system",
    ":common:ui",
    ":common:exception",
    ":feature:social-login",
    ":feature:search",
    ":feature:terms",
)