import com.nugu.nuguollim.plugin.VersionConstants
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("nuguollim.android.application")
    id("nuguollim.android.application.compose")
    id("nuguollim.android.hilt")
    id("org.jetbrains.kotlin.android")
}

android {
    defaultConfig {
        applicationId = "com.nugu.nuguollim"
        versionCode = VersionConstants.VERSION_CODE
        versionName = VersionConstants.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
    }

    signingConfigs {
        val releaseSigningConfig by creating {
            val properties = Properties().apply {
                load(FileInputStream("${rootDir}/local.properties"))
            }
            storeFile = file("${rootDir}/${properties["keystore"]}")
            keyAlias = "${properties["key_alias"]}"
            keyPassword = "${properties["key_password"]}"
            storePassword = "${properties["store_password"]}"
            enableV1Signing = true
            enableV2Signing = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("releaseSigningConfig")
        }
        debug {
            isMinifyEnabled = false
        }
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.common.designSystem)
    implementation(projects.common.ui)
    implementation(projects.common.data)
    implementation(projects.common.systemInfo)
    implementation(projects.feature.socialLogin)
    implementation(projects.feature.search)
    implementation(projects.feature.onboard)
    implementation(projects.feature.myDetail)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.lifecycle.runtime)
    implementation(libs.androidx.compose.lifecycle.viewmodel)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.compiler)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.compose.hilt.navigation)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material.compose)
    implementation(libs.coil)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}