package com.nugu.nuguollim

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.core.content.FileProvider
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import com.nugu.nuguollim.navigation.HomeNavHost
import com.nugu.nuguollim.util.MediaUtil
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NuguollimTheme {
                HomeNavHost(
                    onClickTemplate = { startTemplateDetailActivity(it) },
                    onClickImageSave = { saveImage(it) },
                    onClickImageShare = { shareImage(it) }
                )
            }
        }
    }

    private fun startTemplateDetailActivity(template: Template) {
        val intent = Intent(this, MessageActivity::class.java)
        intent.putExtra("template", template)
        startActivity(intent)
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
        MediaUtil.saveImageLocal(this, bitmap)
    }
}