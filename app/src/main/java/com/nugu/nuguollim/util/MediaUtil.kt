package com.nugu.nuguollim.util

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object MediaUtil {

    fun Context.shareText(text: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }

    fun Context.saveImage(bitmap: ImageBitmap) {
        val saveLocal = saveImageLocal(this, bitmap)

        if (saveLocal) {
            Toast.makeText(this, "이미지 저장 성공", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "이미지 저장 실패", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * share image bitmap
     */
    fun Context.shareImage(imageBitmap: ImageBitmap) {
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

    fun saveImageLocal(context: Context, imageBitmap: ImageBitmap): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            saveImageLocalAboveQ(context.contentResolver, imageBitmap)
        } else {
            saveImageLocalUnderQ(context, imageBitmap)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun saveImageLocalAboveQ(
        contentResolver: ContentResolver,
        imageBitmap: ImageBitmap
    ): Boolean {
        val bitmap = imageBitmap.asAndroidBitmap()
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, getImageName())
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        val uri: Uri? =
            contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        var isSuccess = false
        uri?.let {
            val outputStream = contentResolver.openOutputStream(it)
            outputStream?.let { os ->
                isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
                os.close()
            }
        }

        return isSuccess
    }

    private fun saveImageLocalUnderQ(context: Context, imageBitmap: ImageBitmap): Boolean {
        val bitmap = imageBitmap.asAndroidBitmap()
        val path =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath
        val dir = File("$path/saved_images")
        dir.mkdirs()

        val fileName = getImageName()
        val file = File(dir, fileName)


        var isSuccess = false
        try {
            val outputStream = FileOutputStream(file)
            isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()

            // 이미지가 갤러리에 즉시 표시되도록 합니다.
            MediaScannerConnection.scanFile(context, arrayOf(file.toString()), null) { _, _ ->

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return isSuccess
    }

    private fun getImageName(): String {
        return "image_${System.currentTimeMillis()}.jpg"
    }
}