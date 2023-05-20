import com.android.build.api.dsl.LibraryExtension
import com.nugu.nuguollim.plugin.VersionConstants
import com.nugu.nuguollim.plugin.configureBuildConfig
import com.nugu.nuguollim.plugin.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureBuildConfig()
                defaultConfig.targetSdk = VersionConstants.TARGET_SDK
            }
        }
    }
}