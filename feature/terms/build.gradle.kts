plugins {
    id("nuguollim.android.library")
    id("nuguollim.android.hilt")
}

dependencies {
    implementation(projects.core.remote)
    implementation(projects.core.data)


    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.converter.scalars)
    implementation(libs.retrofit.logging)
}