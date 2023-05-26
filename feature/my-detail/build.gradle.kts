plugins {
    id("nuguollim.android.library")
    id("nuguollim.android.library.compose")
    id("nuguollim.android.hilt")
}

dependencies {
    implementation(projects.core.remote)
    implementation(projects.core.data)
    implementation(projects.common.designSystem)
    implementation(projects.common.uiCore)
    implementation(projects.common.data)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.lifecycle.runtime)
    implementation(libs.androidx.compose.lifecycle.viewmodel)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.compiler)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material.compose)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.compose.hilt.navigation)
    implementation(libs.androidx.paging)
    implementation(libs.androidx.paging.compose)
    implementation(libs.coil)
    implementation(libs.gson)
    implementation(libs.rich.editor)
    implementation(libs.capturable)
}