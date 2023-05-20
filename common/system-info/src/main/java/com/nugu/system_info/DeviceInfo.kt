package com.nugu.system_info

import android.os.Build

object DeviceInfo {

    val deviceModel: String get() = Build.MODEL
    val deviceSdkVersion: Int get() = Build.VERSION.SDK_INT
    val appVersion: String get() = BuildConfig.VERSION_NAME
    val appVersionCode: Int get() = BuildConfig.VERSION_CODE

}