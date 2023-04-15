plugins {
    id("nuguollim.kotlin.library")
}

dependencies {
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.converter.scalars)
    implementation(libs.retrofit.logging)

    implementation(libs.coroutine.core)

    implementation(libs.okhttp)
    implementation(libs.gson)
    implementation(libs.json)
    implementation(libs.javax)
}