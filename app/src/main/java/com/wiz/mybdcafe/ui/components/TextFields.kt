package com.wiz.mybdcafe.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.wiz.mybdcafe.R
import com.wiz.mybdcafe.ui.theme.NanumNeo

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    inputText: String,
    onTextChange: (String) -> Unit,
    labelText: String,
    isError: Boolean = false,
    headingIcon: (@Composable () -> Unit)? = null,
    trailingIconButton: Painter? = null,
    onTrailingButtonClick: (() -> Unit)? = null,
    iconDivider: Boolean = false
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (textField, errorMessage, headingIconRef) = createRefs()

        //헤딩 아이콘
        headingIcon?.let {
            Box(modifier = Modifier
                .constrainAs(headingIconRef) {
                    start.linkTo(parent.start)
                    end.linkTo(textField.start) // B와의 간격을 추가
                    centerVerticallyTo(textField) // B와 수직 중앙 정렬
                }
            ) {
                it()
            }
        }

        //입력칸
        Box(modifier = Modifier
            .constrainAs(textField) {
                top.linkTo(parent.top)
                if (headingIcon != null) { // headingIcon이 존재할 경우
                    start.linkTo(headingIconRef.end, margin = 8.dp)
                } else { // headingIcon이 존재하지 않을 경우
                    start.linkTo(parent.start)
                }
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        ) {
            TextFieldUI(
                modifier = Modifier,
                inputText = inputText,
                onTextChange = onTextChange,
                labelText = labelText,
                isError = isError,
                trailingIconButton = trailingIconButton,
                onTrailingButtonClick = onTrailingButtonClick,
                iconDivider = iconDivider
            )
        }

        //에러 메시지
        Box(modifier = Modifier
            .constrainAs(errorMessage) {
                bottom.linkTo(textField.top, margin = 4.dp)
                start.linkTo(textField.start)
            }
        ) {
            TextFieldErrorMessage(
                labelText = labelText,
                isError = isError
            )
        }
    }
}

@Composable
fun TextFieldUI(
    modifier: Modifier = Modifier,
    inputText: String,
    onTextChange: (String) -> Unit,
    labelText: String,
    isError: Boolean,
    trailingIconButton: Painter?,
    onTrailingButtonClick: (() -> Unit)?,
    iconDivider: Boolean
) {
    //배경 UI
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .border(
                width = if (isError) 2.dp else 1.dp,
                color = if (isError) colorResource(id = R.color.red_0)
                else colorResource(id = R.color.gray_400),
                shape = RoundedCornerShape(3.dp)
            )
            .padding(
                start = 12.dp,
                end = 8.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //텍스트 가운데 정렬용 Row
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicTextField(
                value = inputText,
                onValueChange = onTextChange,
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f),
                singleLine = true,
                decorationBox = { innerTextField ->
                    if (inputText.isEmpty()) {
                        //텍스트 왼쪽 정렬용 column
                        Column {
                            //힌트 텍스트
                            Text(
                                text = labelText,
                                modifier = Modifier
                                    .wrapContentSize(),
                                color = colorResource(id = R.color.gray_400),
                                fontSize = 16.sp,
                                fontFamily = NanumNeo,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    innerTextField()
                }
            )
        }

        //트레일링 아이콘
        if (trailingIconButton != null) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        vertical = 8.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                //icon divider
                if (iconDivider) {
                    Divider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp),
                        color = if (isError) colorResource(id = R.color.red_0)
                        else colorResource(id = R.color.gray_400)
                    )
                }

                TransparentButton(
                    onClick = onTrailingButtonClick ?: {},
                    buttonHeight = 24,
                    buttonWidth = 24,
                    iconResource = trailingIconButton,
                    iconColor = if (isError) colorResource(id = R.color.red_0)
                    else colorResource(id = R.color.gray_400)
                )
            }
        }
    }
}

@Composable
fun TextFieldErrorMessage(
    labelText: String,
    isError: Boolean,
) {
    fun isTextHaveFinalConsonant(text: String): Boolean {
        val lastChar = text.last()

        return (lastChar - '가') % 28 > 0
    }

    fun getPostPosition(text: String): String {
        return if (isTextHaveFinalConsonant(text)) "${text}은" else "${text}는"
    }

    Text(
        //에러 메시지 없을 때도 에러 메시지 표시할 공간 남기기
        text = if (isError) "${getPostPosition(labelText)} 필수 입력 항목 입니다."
        else "",
        color = colorResource(id = R.color.red_0),
        fontSize = 10.sp,
        fontFamily = NanumNeo,
        fontWeight = FontWeight.Bold,
    )
}

@Preview(
    showBackground = true,
    heightDp = 240,
    widthDp = 600
)
@Composable
private fun TextFieldErrorPreview() {
    TextField(
        inputText = "",
        onTextChange = {},
        isError = true,
        labelText = "행사 이름",
        headingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_candlestick_chart),
                contentDescription = null
            )
        },
        trailingIconButton = painterResource(id = R.drawable.ic_add),
        iconDivider = true
    )
}

@Preview(
    showBackground = true,
    heightDp = 240,
    widthDp = 480
)
@Composable
private fun TextFieldDefaultPreview() {
    TextField(
        inputText = "",
        onTextChange = {},
        labelText = "행사 이름",
        trailingIconButton = painterResource(id = R.drawable.ic_add),
    )
}