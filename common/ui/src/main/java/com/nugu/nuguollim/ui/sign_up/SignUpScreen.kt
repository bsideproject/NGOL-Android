package com.nugu.nuguollim.ui.sign_up

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.nugu.nuguollim.common.data.model.terms.Terms
import com.nugu.nuguollim.design_system.component.NuguFillButton
import com.nugu.nuguollim.design_system.component.NuguNameTextField
import com.nugu.nuguollim.design_system.component.TermsCheckBox
import com.nugu.nuguollim.design_system.theme.pretendard
import com.nugu.nuguollim.ui.DevicePreviews
import com.nugu.nuguollim.common.data.model.auth.TokenData
import com.nuguollim.data.state.ResultState
import com.nuguollim.data.usecase.auth.AuthProvide

@Composable
fun SignUpRoute(
    navController: NavHostController,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val activity = LocalContext.current as ComponentActivity
    val provideType = AuthProvide.Type(activity.intent.getStringExtra("provideType") ?: "")
    val provideId = AuthProvide.Id(activity.intent.getStringExtra("provideId") ?: "")

    val termsList by viewModel.termsList.collectAsStateWithLifecycle()
    val signUpState by viewModel.signUpState.collectAsStateWithLifecycle()
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    if (loginState is ResultState.Success) {
        LaunchedEffect(loginState) {
            val data = (loginState as ResultState.Success<TokenData>).data
            viewModel.setToken(AuthProvide.Token(data.token))
            navController.navigate("welcome")
        }
    }

    if (signUpState is ResultState.Success) {
        LaunchedEffect(signUpState) {
            viewModel.setAuthInfo(provideType, provideId)
            viewModel.login(provideType, provideId)
        }
    }

    SignUpScreen(termsList) { terms, provideName ->
        val signupTermsList = terms.filter { it.value }.map { AuthProvide.Terms(it.key.id) }.toList()
        viewModel.signUp(provideType, provideId, provideName, signupTermsList)
    }
}

@Composable
fun SignUpScreen(
    termsList: List<Terms>,
    onClickSignUp: (Map<Terms, Boolean>, AuthProvide.Name) -> Unit,
) {
    var name by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 20.dp, vertical = 18.dp),
    ) {
        Column(modifier = Modifier.align(Alignment.TopCenter)) {
            Spacer(modifier = Modifier.height(188.dp))
            Text(
                text = "편지에 쓸 내 이름,\n혹은 닉네임을 설정해주세요.",
                color = Color(0xFF151516),
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 26.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            NuguNameTextField(
                name = name,
                onValueChange = { name = it }
            )
        }

        if (name.isNotEmpty()) {
            TermsMenu(
                modifier = Modifier.align(Alignment.BottomCenter),
                termsList = termsList,
                onClickTerms = {},
                onClickSignUp = { onClickSignUp(it, AuthProvide.Name(name)) }
            )
        }
    }
}

@Composable
fun TermsMenu(
    modifier: Modifier = Modifier,
    termsList: List<Terms> = emptyList(),
    onClickTerms: (Long) -> Unit = {},
    onClickSignUp: (Map<Terms, Boolean>) -> Unit = {}
) {
    Column(
        modifier = modifier
            .background(
                color = Color(0xFFF9F9F9),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(20.dp)
    ) {
        var checkAll by remember { mutableStateOf(false) }
        val checkTermsMap = remember {
            mutableStateMapOf<Terms, Boolean>().also { map ->
                termsList.forEach { map[it] = false }
            }
        }
        val canSignUp = checkTermsMap
            .filter { it.key.termsType == "필수" }
            .all { it.value }

        TermsCheckBox(
            modifier = Modifier.fillMaxWidth(),
            text = "전체 동의하기",
            isTotal = true,
            checked = checkAll,
            onChecked = {
                checkAll = it
                termsList.forEach { terms -> checkTermsMap[terms] = it }
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(termsList) { terms ->
                val checked = checkTermsMap[terms] ?: false

                TermsCheckBox(
                    modifier = Modifier.fillMaxWidth(),
                    text = terms.title,
                    checked = checked,
                    onChecked = { isCheck -> checkTermsMap[terms] = isCheck },
                    onClickMore = { onClickTerms(terms.id) }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        NuguFillButton(
            text = "가입 완료하기",
            active = canSignUp,
            onClick = { onClickSignUp(checkTermsMap.toMap()) })
    }
}

@DevicePreviews
@Composable
private fun SignUpScreenPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignUpScreen(emptyList()) { _, _ -> }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun TermsMenuPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TermsMenu()
    }
}