package com.wiz.mybdcafe.ui.screens.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiz.mybdcafe.R
import com.wiz.mybdcafe.ui.theme.NanumNeo
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

enum class CalendarState {
    COLLAPSED, DEFAULT, EXPANDED
}

@Composable
fun WeeklyCalendar(
    modifier: Modifier = Modifier,
) {
    val today = LocalDate.now()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월")
    val formattedToday = today.format(dateFormatter)

    val monday = today.minusDays((today.dayOfWeek.value - DayOfWeek.MONDAY.value).toLong())
    val weekDays = mutableListOf<Pair<String, String>>()
    for (i in 0..6) {
        val day = monday.plusDays(i.toLong())
        weekDays.add(
            Pair(
                day.format(DateTimeFormatter.ofPattern("EEE")).uppercase(),
                day.format(DateTimeFormatter.ofPattern("dd"))
            )
        )
    }

    /*
    * TODO: 기획대로 3단계 상태 구현하기
    */

    var calendarState by remember { mutableStateOf(CalendarState.DEFAULT) }

    val initialHeight = remember { mutableStateOf<Dp?>(null) }
    val targetHeight = remember { mutableStateOf(130.dp) }
    val animatedHeight by animateDpAsState(targetValue = targetHeight.value, label = "")

    val toggleCalendarState: () -> Unit = {
        calendarState = when (calendarState) {
            CalendarState.COLLAPSED -> CalendarState.DEFAULT
            CalendarState.DEFAULT -> CalendarState.COLLAPSED
            CalendarState.EXPANDED -> CalendarState.DEFAULT
        }

        targetHeight.value = when (calendarState) {
            CalendarState.COLLAPSED -> 0.dp
            CalendarState.DEFAULT -> initialHeight.value ?: 148.dp
            CalendarState.EXPANDED -> 400.dp
        }
    }

    val density = LocalDensity.current


    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = colorResource(id = R.color.gray_100),
                shape = RoundedCornerShape(
                    bottomStart = 10.dp,
                    bottomEnd = 10.dp
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        //핸들 제외한 내용물
        Column(
            modifier = Modifier
                .animateContentSize()
                .padding(
                    top = 4.dp,
                    start = 2.dp,
                    end = 2.dp,
                )
                .clickable { }
                .onGloballyPositioned {
                    if (initialHeight.value == null) {
                        val height = with(density) { it.size.height.toDp() }

                        initialHeight.value = height
                        targetHeight.value = height
                    }
                }
                .then(
                    Modifier.height(animatedHeight)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //연월주차 텍스트
            Text(
                text = formattedToday,
                modifier = Modifier,
                color = colorResource(id = R.color.gray_600),
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = NanumNeo,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )

            //일주일 날짜 칸들
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                weekDays.forEach { day ->
                    WeeklyCalendarDay(
                        modifier = Modifier
                            .weight(1f),
                        dayTitle = day.first,
                        dayNum = day.second
                    )
                }
            }
        }

        //핸들
        WeeklyCalendarPanelHandle(toggleCalendarState)
    }
}

@Composable
fun WeeklyCalendarDay(
    modifier: Modifier = Modifier,
    dayTitle: String,
    dayNum: String,
) {
    Column(
        modifier = modifier
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //요일 텍스트
        Text(
            text = dayTitle,
            modifier = Modifier
                .wrapContentHeight(),
            color = colorResource(id = R.color.gray_400),
            fontSize = 8.sp,
            fontFamily = NanumNeo,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        //날짜 박스 부분
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(3.dp)
                )
                .padding(vertical = 2.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            //날짜 텍스트
            Text(
                text = dayNum,
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.Black,
                fontSize = 12.sp,
                fontFamily = NanumNeo,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center
            )

            //스케쥴 배치용 빈 공간
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(67.dp)
            )
        }
    }
}

@Composable
fun WeeklyCalendarPanelHandle(
    toggleState: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .background(
                color = colorResource(id = R.color.gray_100),
                shape = RoundedCornerShape(
                    bottomStart = 10.dp,
                    bottomEnd = 10.dp
                )
            )
            .clickable(onClick = toggleState),
        contentAlignment = Alignment.Center
    ) {
        Spacer(
            modifier = Modifier
                .height(4.dp)
                .width(32.dp)
                .background(
                    color = colorResource(id = R.color.gray_400),
                    shape = RoundedCornerShape(50)
                )
        )
    }
}

@Preview
@Composable
private fun WeeklyCalendarPreview() {
    WeeklyCalendar()
}

@Preview(
    widthDp = 50, heightDp = 100,
    showBackground = true, backgroundColor = 0xF3F4F8
)
@Composable
private fun WeeklyCalendarDayPreview() {
    WeeklyCalendarDay(
        dayTitle = "MON",
        dayNum = "01"
    )
}

@Preview
@Composable
private fun WeeklyCalendarPanelHandlePreview() {
    WeeklyCalendarPanelHandle({})
}