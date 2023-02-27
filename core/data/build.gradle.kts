plugins {
    id("nuguollim.kotlin.library")
}

dependencies {
    implementation(projects.core.remote)

    implementation(libs.coroutine.core)
    implementation(libs.javax)
}