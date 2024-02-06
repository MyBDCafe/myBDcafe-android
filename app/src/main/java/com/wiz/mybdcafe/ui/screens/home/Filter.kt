package com.wiz.mybdcafe.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiz.mybdcafe.R
import com.wiz.mybdcafe.ui.components.TransparentButton
import com.wiz.mybdcafe.ui.theme.NanumNeo

@Composable
fun Filter(
    groupTitle: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.CenterVertically)
            .background(Color.White)
            .padding(
                horizontal = 8.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        //장르 선택용 버튼
        Button(
            onClick = { /*TODO*/ },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = groupTitle,
                color = Color.Black,
                fontSize = 24.sp,
                fontFamily = NanumNeo,
                fontWeight = FontWeight.ExtraBold,
            )

            Spacer(modifier = Modifier.width(4.dp))

            Icon(
                painter = painterResource(id = R.drawable.ic_expand_circle_down),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = Color.Black
            )
        }

        //필터 아이콘
        TransparentButton(
            onClick = { /*TODO*/ },
            iconResource = painterResource(id = R.drawable.ic_candlestick_chart),
            iconColor = colorResource(id = R.color.gray_200)
        )
    }
}

@Preview
@Composable
private fun FilterPreview(){
    Filter(
        groupTitle = "PLAVE"
    )
}