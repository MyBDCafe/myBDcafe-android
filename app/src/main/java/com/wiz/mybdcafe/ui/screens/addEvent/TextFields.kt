package com.wiz.mybdcafe.ui.screens.addEvent

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.wiz.mybdcafe.R
import com.wiz.mybdcafe.ui.components.TransparentButton
import com.wiz.mybdcafe.ui.theme.NanumNeo

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    isNecessary: Boolean = false,
    inputText: String,
    onTextChange: (String) -> Unit,
    labelText: String,
    headingIcon: (@Composable () -> Unit)? = null,
    trailingIconButton: (@Composable () -> Unit)? = null,
    iconDivider: Boolean = false,
    focusManager: FocusManager,
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
                    end.linkTo(textField.start)
                    centerVerticallyTo(textField) // 텍스트필드와 수직 중앙 정렬
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
            TextFieldBackgroundBox(
                modifier = Modifier,
                isNecessary = isNecessary,
                isInput = inputText.isNotBlank(),
                hintText = if (inputText.isBlank()) labelText else "",
                trailingIconButton = trailingIconButton,
                iconDivider = iconDivider,
                content = {
                    BasicTextField(
                        value = inputText,
                        onValueChange = onTextChange,
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                focusManager.clearFocus()
                            },
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                        singleLine = true,
                    )
                }
            )
        }

        //에러 메시지
        Box(modifier = Modifier
            .constrainAs(errorMessage) {
                bottom.linkTo(textField.top, margin = 4.dp) //헤딩 아이콘 사이 간격
                start.linkTo(textField.start)
            }
        ) {
            TextFieldErrorMessage(
                labelText = labelText,
                isError = isNecessary && inputText.isBlank()
            )
        }
    }
}

@Composable
fun TextFieldBackgroundBox(
    modifier: Modifier = Modifier,
    isNecessary: Boolean = false,
    isInput: Boolean = false,
    hintText: String? = null,
    trailingIconButton: (@Composable () -> Unit)? = null,
    iconDivider: Boolean = false,
    content: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .border(
                width = if (isNecessary && !isInput) 2.dp else 1.dp,
                color = if (isNecessary && !isInput) colorResource(id = R.color.red_0)
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
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            if (!hintText.isNullOrBlank()) {
                Text(
                    text = hintText,
                    modifier = Modifier
                        .wrapContentSize(),
                    color = colorResource(id = R.color.gray_400),
                    fontSize = 16.sp,
                    fontFamily = NanumNeo,
                    fontWeight = FontWeight.Bold
                )
            }

            //박스에 들어갈 요소
            //ex) Text, TextField...
            if (content != null) {
                content()
            }
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
                        color = if (isNecessary && !isInput) colorResource(id = R.color.red_0)
                        else colorResource(id = R.color.gray_400)
                    )
                }

                trailingIconButton()
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

@Preview
@Composable
private fun TextFieldBackgroundBoxPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        TextFieldBackgroundBox(
            hintText = "테스트"
        )

        TextFieldBackgroundBox(
            hintText = "테스트",
            trailingIconButton = {
                TransparentButton(
                    onClick = { /*TODO*/ },
                    iconResource = painterResource(id = R.drawable.ic_add),
                    buttonWidth = 24,
                    buttonHeight = 24,
                    iconHeight = 18,
                    iconWidth = 18
                )
            },
            iconDivider = true
        )
    }
}

@Preview
@Composable
private fun TextFieldPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        TextField(
            inputText = "",
            onTextChange = {},
            labelText = "테스트",
            focusManager = LocalFocusManager.current
        )

        TextField(
            isNecessary = true,
            inputText = "",
            onTextChange = {},
            labelText = "테스트",
            headingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_candlestick_chart),
                    contentDescription = null
                )
            },
            trailingIconButton = {
                TransparentButton(
                    onClick = { /*TODO*/ },
                    iconResource = painterResource(id = R.drawable.ic_add),
                    buttonWidth = 24,
                    buttonHeight = 24,
                    iconHeight = 18,
                    iconWidth = 18
                )
            },
            iconDivider = true,
            focusManager = LocalFocusManager.current
        )
    }
}