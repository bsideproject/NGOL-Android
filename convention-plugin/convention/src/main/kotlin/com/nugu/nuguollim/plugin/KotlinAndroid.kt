package com.nugu.nuguollim.plugin

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = VersionConstants.TARGET_SDK

        defaultConfig {
            minSdk = VersionConstants.MIN_SDK
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        kotlinOptions {
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()

            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                // Enable experimental coroutines APIs, including Flow
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-opt-in=kotlin.Experimental",
            )

            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
}

internal fun LibraryExtension.configureBuildConfig() {
    buildTypes {
        release {
            buildConfigField("String", "VERSION_NAME", "\"${VersionConstants.VERSION_NAME}\"")
            buildConfigField("Integer", "VERSION_CODE", "${VersionConstants.VERSION_CODE}")
        }
        debug {
            buildConfigField("String", "VERSION_NAME", "\"${VersionConstants.VERSION_NAME}\"")
            buildConfigField("Integer", "VERSION_CODE", "${VersionConstants.VERSION_CODE}")
        }
    }
}

fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}