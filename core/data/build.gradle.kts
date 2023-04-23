plugins {
    id("nuguollim.kotlin.library")
}

dependencies {
    implementation(projects.core.remote)
    implementation(projects.core.dataStore)
    implementation(projects.core.config)
    implementation(projects.common.data)

    implementation(libs.coroutine.core)
    implementation(libs.javax)

    implementation(libs.okhttp)
    implementation(libs.json)
}