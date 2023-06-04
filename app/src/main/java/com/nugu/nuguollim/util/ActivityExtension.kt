package com.nugu.nuguollim.util

import android.content.Intent
import android.os.Build
import androidx.activity.ComponentActivity
import com.nugu.nuguollim.MessageActivity
import com.nugu.nuguollim.common.data.model.template.MyWritingTemplateData

fun ComponentActivity.getMyWritingTemplate(): MyWritingTemplateData =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra(
            "myWritingTemplateData",
            MyWritingTemplateData::class.java
        )!!
    } else {
        intent.getSerializableExtra("myWritingTemplateData") as MyWritingTemplateData
    }

fun ComponentActivity.startTemplateDetailActivity(myWritingTemplateData: MyWritingTemplateData) {
    val intent = Intent(this, MessageActivity::class.java)
    intent.putExtra("myWritingTemplateData", myWritingTemplateData)
    startActivity(intent)
}