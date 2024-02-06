package com.wiz.mybdcafe.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wiz.mybdcafe.R

@Composable
fun TransparentButton(
    onClick: () -> Unit,
    buttonHeight: Int = 48,
    buttonWidth: Int = 48,
    iconResource: Painter,
    iconColor: Color = Color.Black,
    iconHeight: Int = 24,
    iconWidth: Int = 24
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(buttonHeight.dp)
            .width(buttonWidth.dp),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            painter = iconResource,
            contentDescription = null,
            modifier = Modifier
                .height(iconHeight.dp)
                .width(iconWidth.dp),
            tint = iconColor
        )
    }
}

@Preview
@Composable
private fun TransparentButtonPreview() {
    TransparentButton(
        onClick = {},
        iconResource = painterResource(id = R.drawable.ic_expand_circle_down)
    )
}