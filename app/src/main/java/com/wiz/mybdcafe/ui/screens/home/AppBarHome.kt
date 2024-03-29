package com.wiz.mybdcafe.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiz.mybdcafe.R
import com.wiz.mybdcafe.ui.theme.NanumNeo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarHome() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name_kr),
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
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        )
    )
}

@Preview
@Composable
private fun AppbarHomePreview() {
    AppBarHome()
}