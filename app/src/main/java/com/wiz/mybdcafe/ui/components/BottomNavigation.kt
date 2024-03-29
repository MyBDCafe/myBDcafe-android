package com.wiz.mybdcafe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wiz.mybdcafe.R

@Composable
fun BottomNavigation() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(Color.White)
    ) {

    }
}

@Composable
private fun NavigationButton(
    modifier: Modifier = Modifier,
    buttonIcon: Painter,
    buttonTitle: String,
) {
    Button(
        onClick = { /*TODO*/ },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RectangleShape,
        contentPadding = PaddingValues(0.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = buttonIcon,
                contentDescription = null,
            )

            Text(
                text = buttonTitle
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavigationPreview() {
    BottomNavigation()
}

@Preview
@Composable
private fun NavigationButtonPreview() {
    NavigationButton(
        buttonIcon = painterResource(id = R.drawable.ic_candlestick_chart),
        buttonTitle = "임시"
    )
}