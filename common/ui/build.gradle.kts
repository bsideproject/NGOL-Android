plugins {
    id("nuguollim.android.library")
    id("nuguollim.android.library.compose")
    id("nuguollim.android.hilt")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.lifecycle.runtime)
    implementation(libs.androidx.compose.lifecycle.viewmodel)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.compiler)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material.compose)
    implementation(libs.coil)
    implementation(libs.system.ui.controller)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.compose.hilt.navigation)
    testImplementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.compose)

    implementation(projects.common.designSystem)
    implementation(projects.feature.socialLogin)
    implementation(projects.core.data)
    implementation(projects.common.di)
    implementation(projects.common.exception)
    implementation(projects.common.data)
    implementation(projects.core.config)
}