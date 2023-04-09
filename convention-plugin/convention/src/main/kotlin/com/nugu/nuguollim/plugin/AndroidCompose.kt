package com.nugu.nuguollim.plugin

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import java.io.File

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *>
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("androidxCompose").get().toString()
        }
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + buildComposeMetricsParameters()
        }
    }
}

private fun Project.buildComposeMetricsParameters(): List<String> {
    val metricParameters = mutableListOf<String>()
    val enableMetricsProvider = project.providers.gradleProperty("enableComposeCompilerMetrics")
    val enableMetrics = (enableMetricsProvider.orNull == "true")
    if (enableMetrics) {
        val metricsFolder = File(project.buildDir, "compose-metrics")
        metricParameters += "-P"
        metricParameters += "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${metricsFolder.absolutePath}"
    }

    val enableReportsProvider = project.providers.gradleProperty("enableComposeCompilerReports")
    val enableReports = (enableReportsProvider.orNull == "true")
    if (enableReports) {
        val reportsFolder = File(project.buildDir, "compose-reports")
        metricParameters += "-P"
        metricParameters += "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${reportsFolder.absolutePath}"
    }

    metricParameters += "-opt-in=androidx.compose.material.ExperimentalMaterialApi"

    return metricParameters.toList()
}