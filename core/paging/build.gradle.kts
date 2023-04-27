plugins {
    id("nuguollim.android.library")
    id("nuguollim.android.hilt")
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.common.data)

    implementation(libs.androidx.paging)
    implementation(libs.androidx.paging.compose)
}