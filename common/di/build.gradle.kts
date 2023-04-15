plugins {
    id("nuguollim.android.library")
    id("nuguollim.android.hilt")
}

dependencies {
    implementation(projects.core.remote)
    implementation(projects.core.data)
    implementation(projects.core.repository)

    implementation(libs.okhttp)
    implementation(libs.retrofit.logging)
}