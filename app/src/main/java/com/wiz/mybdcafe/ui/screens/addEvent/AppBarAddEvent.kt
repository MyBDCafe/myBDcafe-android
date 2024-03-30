package com.wiz.mybdcafe.ui.screens.addEvent

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiz.mybdcafe.R
import com.wiz.mybdcafe.ui.components.TransparentButton
import com.wiz.mybdcafe.ui.theme.NanumNeo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarAddEvent() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "행사 추가",
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(align = Alignment.CenterVertically),
                color = colorResource(id = R.color.red_0),
                fontSize = 15.sp,
                fontFamily = NanumNeo,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
            )
        },
        modifier = Modifier
            .height(52.dp)
            .fillMaxWidth(),
        navigationIcon = {
            TransparentButton(
                onClick = { /*TODO*/ },
                buttonHeight = 52,
                buttonWidth = 52,
                iconResource = painterResource(id = R.drawable.ic_arrow_back)
            )
        }
    )
}

@Preview
@Composable
private fun AppBarAddEventPreview(){
    AppBarAddEvent()
}