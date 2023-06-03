package com.nugu.nuguollim.util

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object MediaUtil {

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