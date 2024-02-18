package com.wiz.mybdcafe.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.wiz.mybdcafe.R

@Composable
fun FAB(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        containerColor = colorResource(id = R.color.red_0),
        contentColor = Color.White
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun FABPreview() {
    FAB {}
}