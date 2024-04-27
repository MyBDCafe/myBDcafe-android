package com.wiz.mybdcafe.ui.screens.addEvent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiz.mybdcafe.R
import com.wiz.mybdcafe.ui.components.ButtonBottomBar
import com.wiz.mybdcafe.ui.components.TransparentButton
import com.wiz.mybdcafe.ui.theme.NanumNeo

@Composable
fun AddEvent() {
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier
            .clickable(
                onClick = { focusManager.clearFocus() },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        topBar = { AppBarAddEvent() },
        bottomBar = {
            ButtonBottomBar(
                buttonText = "행사 저장"
            )
        }
    ) { innerValues ->
        Column(
            modifier = Modifier
                .padding(innerValues)
                .padding(
                    top = 12.dp,
                    start = 12.dp,
                    end = 12.dp
                )
        ) {
            Text(
                text = "행사 추가하기",
                fontSize = 20.sp,
                fontFamily = NanumNeo,
                fontWeight = FontWeight.ExtraBold,
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )

            Text(
                text = "* 표시된 항목은 필수로 입력해주세요",
                color = colorResource(id = R.color.gray_500),
                fontSize = 12.sp,
                fontFamily = NanumNeo,
                fontWeight = FontWeight.Bold,
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            )

            //정보 입력 칸들
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 24.dp
                    ),
            ) {
                //행사 이름
                var eventName by rememberSaveable { mutableStateOf("") }

                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    isNecessary = true,
                    inputText = eventName,
                    onTextChange = { eventName = it },
                    labelText = "행사 이름",
                    trailingIconButton = {
                        TransparentButton(
                            onClick = { /*TODO*/ },
                            iconResource = painterResource(id = R.drawable.ic_circle_filled),
                            buttonWidth = 24,
                            buttonHeight = 24,
                            iconHeight = 18,
                            iconWidth = 18
                        )
                    },
                    iconDivider = true,
                    focusManager = focusManager
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp)
                )

                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    //분류 드롭다운 메뉴
                    DropdownMenuWithErrorMessage(
                        hintText = "분류",
                        headingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_filter_alt),
                                contentDescription = null,
                                tint = colorResource(id = R.color.red_0)
                            )
                        }
                    )

                    //장소
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        inputText = "",
                        onTextChange = { },
                        labelText = "장소",
                        headingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_place),
                                contentDescription = null,
                                tint = colorResource(id = R.color.red_0)
                            )
                        },
                        trailingIconButton = {
                            TransparentButton(
                                onClick = { /*TODO*/ },
                                iconResource = painterResource(id = R.drawable.ic_map),
                                iconColor = colorResource(id = R.color.gray_400),
                                buttonWidth = 24,
                                buttonHeight = 24,
                                iconHeight = 18,
                                iconWidth = 18
                            )
                        },
                        focusManager = focusManager
                    )

                    //날짜 및 시간
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        inputText = "",
                        onTextChange = { },
                        labelText = "날짜 및 시간",
                        headingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_clock),
                                contentDescription = null,
                                tint = colorResource(id = R.color.red_0)
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
                        focusManager = focusManager
                    )

                    //태그
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        inputText = "",
                        onTextChange = { },
                        labelText = "태그",
                        headingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_tag),
                                contentDescription = null,
                                tint = colorResource(id = R.color.red_0)
                            )
                        },
                        focusManager = focusManager
                    )

                    //링크
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        inputText = "",
                        onTextChange = { },
                        labelText = "링크",
                        headingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_link),
                                contentDescription = null,
                                tint = colorResource(id = R.color.red_0)
                            )
                        },
                        focusManager = focusManager
                    )

                    //메모
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        inputText = "",
                        onTextChange = { },
                        labelText = "메모",
                        headingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_article),
                                contentDescription = null,
                                tint = colorResource(id = R.color.red_0)
                            )
                        },
                        focusManager = focusManager
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AddEventPreview() {
    AddEvent()
}