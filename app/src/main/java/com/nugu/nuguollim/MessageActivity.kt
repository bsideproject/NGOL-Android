package com.nugu.nuguollim

import android.content.*
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.core.content.FileProvider
import androidx.navigation.compose.rememberNavController
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.common.data.model.template.Writing
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import com.nugu.nuguollim.navigation.MessageNavHost
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


@AndroidEntryPoint
class MessageActivity : ComponentActivity() {

    private val viewModel: MessageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Scaffold(
            ) { innerPadding ->
                NuguollimTheme {
                    MessageNavHost(
                        modifier = Modifier.padding(innerPadding),
                        navHostController = navController,
                        template = getTemplate(),
                        onClickTextCopy = { setClipboardText(it) },
                        onClickTextShare = { shareText(it) },
                        onClickImageSave = { imageBitmap, writing ->
                            saveImage(
                                imageBitmap,
                                writing
                            )
                        },
                        onClickImageShare = { shareImage(it) }
                    )
                }
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

    private fun saveImage(bitmap: ImageBitmap, writing: Writing) {
        saveImageLocal(bitmap)
        saveImageRemote(writing)
    }

    private fun saveImageLocal(imageBitmap: ImageBitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            saveImageLocalAboveQ(imageBitmap)
        } else {
            saveImageLocalUnderQ(imageBitmap)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun saveImageLocalAboveQ(imageBitmap: ImageBitmap) {
        val bitmap = imageBitmap.asAndroidBitmap()
        val resolver = contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, getImageName())
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        val uri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        uri?.let {
            val outputStream = resolver.openOutputStream(it)
            outputStream?.let { os ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
                os.close()
            }
        }
    }

    private fun saveImageLocalUnderQ(imageBitmap: ImageBitmap) {
        val bitmap = imageBitmap.asAndroidBitmap()
        val path =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath
        val dir = File("$path/saved_images")
        dir.mkdirs()

        val fileName = getImageName()
        val file = File(dir, fileName)

        try {
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()

            // 이미지가 갤러리에 즉시 표시되도록 합니다.
            MediaScannerConnection.scanFile(
                this, arrayOf(file.toString()), null
            ) { _, _ ->
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun getImageName(): String {
        return "image_${System.currentTimeMillis()}.jpg"
    }

    private fun saveImageRemote(writing: Writing) {
        viewModel.saveTemplate(writing)
    }
}
