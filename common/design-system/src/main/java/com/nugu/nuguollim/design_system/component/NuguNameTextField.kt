package com.nugu.nuguollim.design_system.component

import android.os.Build
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nugu.nuguollim.design_system.R
import com.nugu.nuguollim.design_system.theme.pretendard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.regex.Pattern

@Composable
fun NuguNameTextField(
    modifier: Modifier = Modifier,
    name: String = "",
    onValueChange: (String) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val offsetX = remember { Animatable(0f) }
    val view = LocalView.current
    val painter = painterResource(id = R.drawable.ic_delete_text)
    val pattern = Pattern.compile("^$|[a-zA-Z0-9ㄱ-힣]+$")
    val maxLength = 6

    Column(
        modifier = modifier
            .offset(offsetX.value.dp, 0.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = {
                val isMatch = pattern.matcher(it).matches()
                val length = it.length
                if (length <= maxLength && isMatch) {
                    onValueChange(it)
                } else if (length > maxLength) {
                    animateView(offsetX, coroutineScope, view)
                }
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = Color(0xFFFF7043),
                    shape = RoundedCornerShape(5.dp)
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(5.dp)
                ),
            placeholder = {
                Text(
                    text = "${maxLength}글자 이내 한글 혹은 영문",
                    fontFamily = pretendard
                )
            },
            trailingIcon = {
                Image(
                    modifier = Modifier
                        .size(16.dp)
                        .clickable {
                            onValueChange("")
                        },
                    painter = painter,
                    contentDescription = "clear name",
                    contentScale = ContentScale.Fit
                )
            },
            textStyle = TextStyle.Default.copy(fontFamily = pretendard)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color(0xFF151516))) {
                    append("${name.length}")
                }
                withStyle(style = SpanStyle(color = Color(0xFF858585))) {
                    append("/${maxLength}")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            fontFamily = pretendard,
            fontWeight = FontWeight.Normal
        )
    }
}

private val shakeKeyFrame: AnimationSpec<Float> = keyframes {
    durationMillis = 500
    val easing = FastOutLinearInEasing

    for (i in 1..8) {
        val x = when (i % 3) {
            0 -> 4f
            1 -> -4f
            else -> 0f
        }
        x at durationMillis / 10 * i with easing
    }
}

private fun animateView(
    offset: Animatable<Float, AnimationVector1D>,
    coroutineScope: CoroutineScope,
    view: View? = null
) {
    coroutineScope.launch {
        offset.animateTo(
            targetValue = 0f,
            animationSpec = shakeKeyFrame
        )
    }
    view?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            view.performHapticFeedback(HapticFeedbackConstants.REJECT)
        } else {
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
        }
    }
}

@Preview("Login help button", showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun NameTextFieldPreview() {
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NuguNameTextField(
            name = name,
            onValueChange = { name = it }
        )
    }
}