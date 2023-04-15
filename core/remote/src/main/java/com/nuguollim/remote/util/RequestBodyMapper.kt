package com.nuguollim.remote.util

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import java.io.File

const val MEDIA_TYPE_JSON = "application/json"

fun JSONObject.toRequestBody(mediaType: String = MEDIA_TYPE_JSON): RequestBody =
    makeRequestBody(makeMediaTypeOrNull(mediaType), this.toString())

private fun makeRequestBody(mediaType: MediaType?, content: String): RequestBody =
    content.toRequestBody(mediaType)

private fun makeRequestBody(mediaType: MediaType?, content: File): RequestBody =
    content.asRequestBody(mediaType)

private fun makeMediaTypeOrNull(mediaType: String): MediaType? =
    mediaType.toMediaTypeOrNull()

private fun makeResponseBody(contentType: MediaType?, content: String) =
    content.toResponseBody(contentType)