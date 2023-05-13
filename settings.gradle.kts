pluginManagement {
    includeBuild("convention-plugin")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://devrepo.kakao.com/nexus/content/groups/public/")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven ( "https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://devrepo.kakao.com/nexus/content/groups/public/")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven ( "https://jitpack.io")
    }
}
rootProject.name = "nuguollim"
include(
    ":app",
    ":core:data",
    ":core:remote",
    ":core:config",
    ":core:data-store",
    ":core:paging",
    ":common:di",
    ":common:design-system",
    ":common:ui",
    ":common:ui-core",
    ":common:exception",
    ":common:data",
    ":feature:social-login",
    ":feature:search",
    ":feature:onboard",
)