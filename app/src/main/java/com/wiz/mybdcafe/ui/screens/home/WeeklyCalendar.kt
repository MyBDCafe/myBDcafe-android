package com.wiz.mybdcafe.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiz.mybdcafe.R
import com.wiz.mybdcafe.ui.theme.NanumNeo

@Composable
fun WeeklyCalendar(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = colorResource(id = R.color.gray_100),
            )
            .padding(
                top = 4.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "2024년 2월 1주",
            modifier = Modifier
            //    .fillMaxWidth()
            ,
            color = colorResource(id = R.color.gray_600),
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = NanumNeo,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun WeeklyCalendarPreview() {
    WeeklyCalendar()
}