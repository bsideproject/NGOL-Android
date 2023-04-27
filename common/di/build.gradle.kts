plugins {
    id("nuguollim.android.library")
    id("nuguollim.android.hilt")
}

dependencies {
    implementation(projects.core.remote)
    implementation(projects.core.data)
    implementation(projects.core.dataStore)
    implementation(projects.core.paging)

    implementation(libs.okhttp)
    implementation(libs.data.store)
    implementation(libs.retrofit.logging)
}