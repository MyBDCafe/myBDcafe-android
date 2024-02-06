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
    height: Int = 24,
    width: Int = 24,
    iconResource: Painter,
    iconColor: Color = Color.Black
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(height.dp)
            .width(width.dp),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            painter = iconResource,
            contentDescription = null,
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