plugins {
    id("nuguollim.android.library")
    id("nuguollim.android.library.compose")
    id("nuguollim.android.hilt")
}

dependencies {
    implementation(projects.core.remote)
    implementation(projects.core.data)

    implementation(libs.javax)
    implementation(libs.naver.login)
    implementation(libs.google.services.auth)
    implementation(libs.kakao.user)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.lifecycle.runtime)
    implementation(libs.androidx.compose.lifecycle.viewmodel)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.compiler)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material.compose)
    implementation(libs.coil)

    implementation(project(mapOf("path" to ":common:design-system")))
}