package com.nugu.nuguollim

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.core.content.FileProvider
import androidx.navigation.compose.rememberNavController
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.navigation.MessageNavHost
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@AndroidEntryPoint
class MessageActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Scaffold(
            ) { innerPadding ->
                MessageNavHost(
                    modifier = Modifier.padding(innerPadding),
                    navHostController = navController,
                    template = getTemplate(),
                    onClickTextCopy = { setClipboardText(it) },
                    onClickTextShare = { shareText(it) },
                    onClickImageSave = {
                        Log.d("save", "${it.width} ${it.height}")
                    },
                    onClickImageShare = { shareImage(it) }
                )
            }
        }
    }

    private fun setClipboardText(text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("nugu message content", text)
        clipboard.setPrimaryClip(clip)
    }

    private fun shareText(text: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }

    private fun getTemplate(): Template {
        val template = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("template", Template::class.java)
        } else {
            intent.getSerializableExtra("template") as Template
        }

        return template ?: Template()
    }

    /**
     * share image bitmap
     */
    private fun shareImage(imageBitmap: ImageBitmap) {
        val bitmap = imageBitmap.asAndroidBitmap()
        try {
            // 이미지 저장
            val cachePath = File(applicationContext.cacheDir, "images")
            cachePath.mkdirs()

            val stream = FileOutputStream("$cachePath/shareImage.png")

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.close()

            // 이미지 공유
            val newFile = File(cachePath, "shareImage.png")
            val contentUri: Uri = FileProvider.getUriForFile(
                applicationContext,
                "com.nugu.nuguollim.provider",
                newFile
            )

            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                // 임시 권한을 부여
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                setDataAndType(contentUri, contentResolver.getType(contentUri))
                putExtra(Intent.EXTRA_STREAM, contentUri)
            }
            startActivity(Intent.createChooser(shareIntent, null))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun saveImage(bitmap: ImageBitmap) {
    }
}
