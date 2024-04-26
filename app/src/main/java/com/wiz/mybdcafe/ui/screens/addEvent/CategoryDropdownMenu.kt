package com.wiz.mybdcafe.ui.screens.addEvent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.wiz.mybdcafe.R
import com.wiz.mybdcafe.ui.theme.NanumNeo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropdownMenu(
    modifier: Modifier = Modifier,
    hintText: String,
    headingIcon: (@Composable () -> Unit)? = null,
) {
    val testOptions = arrayOf("남예준", "한노아", "채밤비", "도은호", "유하민")

    val (isExpanded, setIsExpanded) = remember { mutableStateOf(false) }
    val (selectedText, setSelectedText) = remember { mutableStateOf<String?>(null) }

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {
                setIsExpanded(!isExpanded)
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextFieldBackgroundBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { setIsExpanded(!isExpanded) }
                    .menuAnchor(),
                isNecessary = true,
                isInput = !selectedText.isNullOrBlank(),
                hintText = if (selectedText.isNullOrBlank()) hintText else null,
                trailingIconButton = {
                    if (isExpanded) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_drop_up),
                            contentDescription = null,
                            tint = if (selectedText.isNullOrBlank()) colorResource(id = R.color.red_0)
                            else colorResource(id = R.color.gray_400)
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_drop_down),
                            contentDescription = null,
                            tint = if (selectedText.isNullOrBlank()) colorResource(id = R.color.red_0)
                            else colorResource(id = R.color.gray_400)
                        )
                    }
                }
            ) {
                //선택된 옵션 텍스트 표시
                if (!selectedText.isNullOrBlank()) {
                    Text(
                        text = selectedText,
                        modifier = Modifier
                            .wrapContentSize(),
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = NanumNeo,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { setIsExpanded(false) },
                modifier = Modifier
                    .background(Color.White)
            ) {
                testOptions.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                modifier = Modifier
                                    .wrapContentSize(),
                                //선택된 옵션과 그렇지 않은 옵션 색상 다르게
                                color = if (selectedText != item) Color.Black
                                else colorResource(id = R.color.red_0),
                                fontSize = 16.sp,
                                fontFamily = NanumNeo,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        onClick = {
                            setSelectedText(item)
                            setIsExpanded(false)
                        },
                        modifier = Modifier
                            //선택된 옵션과 그렇지 않은 옵션 색상 다르게
                            .background(
                                if (selectedText != item) Color.White
                                else colorResource(id = R.color.red_0).copy(alpha = 0.15f)
                            )
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    heightDp = 100,
    widthDp = 480
)
@Composable
private fun CategoryDropdownMenuPreview() {
    CategoryDropdownMenu(
        hintText = "분류",
        headingIcon = null
    )
}