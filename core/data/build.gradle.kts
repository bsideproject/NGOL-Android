plugins {
    id("nuguollim.kotlin.library")
}

dependencies {
    implementation(projects.core.remote)

    implementation(libs.coroutine.core)
    implementation(libs.javax)

    implementation(libs.okhttp)
    implementation(libs.json)
}