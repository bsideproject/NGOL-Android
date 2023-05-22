package com.nugu.nuguollim

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NuguollimTheme {
                val context = LocalContext.current

                val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    arrayOf(
                        android.Manifest.permission.READ_MEDIA_IMAGES,
                    )
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    arrayOf(
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    )
                } else {
                    arrayOf(
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    )
                }

                val launcherMultiplePermissions = rememberLauncherForActivityResult(
                    ActivityResultContracts.RequestMultiplePermissions()
                ) { permissionsMap ->
                    val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }

                    if (areGranted.not()) {
                        Toast.makeText(
                            context,
                            "권한이 허용되지 않아 일부 기능이 제한됩니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    val intent = Intent(this, LoginInActivity::class.java)
                    startActivity(intent)
                }

                CheckAndRequestPermissions(context, permissions, launcherMultiplePermissions)
            }
        }
    }

    @Composable
    private fun CheckAndRequestPermissions(
        context: Context,
        permissions: Array<String>,
        launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
    ) {
        val hasAuth = permissions.all {
            ContextCompat.checkSelfPermission(context, it) ==
                    PackageManager.PERMISSION_GRANTED
        }

        if (hasAuth) {
            startApp()
        } else {
            LaunchedEffect(launcher) {
                launcher.launch(permissions)
            }
        }
    }

    private fun startApp() {
        val intent = Intent(this, LoginInActivity::class.java)
        startActivity(intent)
    }
}