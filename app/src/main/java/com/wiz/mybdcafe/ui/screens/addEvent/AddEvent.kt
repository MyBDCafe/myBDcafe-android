package com.wiz.mybdcafe.ui.screens.addEvent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiz.mybdcafe.R
import com.wiz.mybdcafe.ui.theme.NanumNeo

@Composable
fun AddEvent() {
    Scaffold(
        topBar = { AppBarAddEvent() },
        bottomBar = {
            /*TODO: 다른 컴포저블로 분리하기*/

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal = 12.dp,
                            vertical = 8.dp
                        )
                ) {
                    /*TODO: 컴포넌트로 분리하기*/
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxSize(),
                        shape = RoundedCornerShape(3.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.red_0)
                        )
                    ) {
                        Text(
                            text = "행사 저장",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontFamily = NanumNeo,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    ) { innerValues ->
        Column(
            modifier = Modifier
                .padding(innerValues)
                .padding(
                    top = 12.dp,
                    start = 12.dp,
                    end = 12.dp
                )
        ) {
            Text(
                text = "행사 추가하기",
                fontSize = 20.sp,
                fontFamily = NanumNeo,
                fontWeight = FontWeight.ExtraBold,
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )

            Text(
                text = "* 표시된 항목은 필수로 입력해주세요",
                color = colorResource(id = R.color.gray_500),
                fontSize = 12.sp,
                fontFamily = NanumNeo,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Preview
@Composable
private fun AddEventPreview() {
    AddEvent()
}