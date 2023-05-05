package com.nugu.nuguollim

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.design_system.component.NuguMessageToolbar
import com.nugu.nuguollim.navigation.MessageNavHost
import com.nugu.nuguollim.navigation.MessageScreenType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Scaffold(
                topBar = {
                    NuguMessageToolbar(
                        onClickBack = {
                            val currentRoute =
                                navController.currentBackStackEntry?.destination?.route
                            if (currentRoute == MessageScreenType.Detail.screenRoute) {
                                finish()
                            } else {
                                navController.popBackStack()
                            }
                        },
                        onClickClose = { finish() }
                    )
                }
            ) { innerPadding ->
                MessageNavHost(
                    modifier = Modifier.padding(innerPadding),
                    navHostController = navController,
                    template = getTemplate()
                )
            }
        }
    }

    private fun getTemplate(): Template {
        val template = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("template", Template::class.java)
        } else {
            intent.getSerializableExtra("template") as Template
        }

        return template ?: Template()
    }
}
