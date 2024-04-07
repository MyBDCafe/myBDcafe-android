package com.wiz.mybdcafe.ui.screens.home

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
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

    /*
    * TODO: 기획대로 3단계 상태 구현하기
    */

    var calendarState by remember { mutableStateOf(CalendarState.DEFAULT) }
    var initialHeight by remember { mutableStateOf(0.dp) }
    val targetHeight = remember { mutableStateOf(300.dp) }
    val animatedHeight by animateDpAsState(targetValue = targetHeight.value, label = "")

    val toggleCalendarState: () -> Unit = {
        calendarState = when (calendarState) {
            CalendarState.COLLAPSED -> CalendarState.DEFAULT
            CalendarState.DEFAULT -> CalendarState.COLLAPSED
            CalendarState.EXPANDED -> CalendarState.DEFAULT
        }

        targetHeight.value = when (calendarState) {
            CalendarState.COLLAPSED -> 0.dp
            CalendarState.DEFAULT -> initialHeight
            CalendarState.EXPANDED -> 400.dp
        }
    }

    val density = LocalDensity.current
    var titleTextHeight by remember { mutableStateOf(0.dp) }
    var dayTextHeight by remember { mutableStateOf(0.dp) }
    var dateTextHeight by remember { mutableStateOf(0.dp) }

    // 각 텍스트 높이가 결정될 때마다 targetHeight 업데이트
    LaunchedEffect(titleTextHeight, dayTextHeight, dateTextHeight) {
        Log.d("LaunchedEffect(titleTextHeight, dayTextHeight, dateTextHeight)",
            "$titleTextHeight, $dayTextHeight, $dateTextHeight")

        // 모든 높이가 유효할 때(0.dp 이상) targetHeight 업데이트
        if (titleTextHeight > 0.dp && dayTextHeight > 0.dp && dateTextHeight > 0.dp) {
            initialHeight =
                /*TODO: 상수변수로 대체하기*/
                4.dp + 4.dp + titleTextHeight + 8.dp + dayTextHeight + 2.dp + 2.dp + dateTextHeight + 2.dp + (16 * 4 + 3).dp
        }
    }

    LaunchedEffect(initialHeight) {
        Log.d("LaunchedEffect(initialHeight)", "$initialHeight")

        targetHeight.value = initialHeight
    }

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
                .height(animatedHeight)
                .padding(
                    start = 2.dp,
                    end = 2.dp,
                )
                .clickable { },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
            )

            //연월주차 텍스트
            Text(
                text = formattedToday,
                modifier = Modifier
                    .onGloballyPositioned {
                        titleTextHeight = with(density) { it.size.height.toDp() }
                    },
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

            //요일 텍스트
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                val days = arrayOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
                for (day in days) {
                    Text(
                        text = day,
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .weight(1f)
                            .onGloballyPositioned {
                                dayTextHeight = with(density) { it.size.height.toDp() }
                            },
                        color = colorResource(id = R.color.gray_400),
                        fontSize = 8.sp,
                        fontFamily = NanumNeo,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }

            //스케줄 칸들
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                //.background(Color.Cyan)
            ) {
                //흰색 배경
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    for (i in 0..6) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(3.dp)
                                )
                        )
                    }
                }

                //날짜 텍스트 + 스케줄 배치 공간
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        //.background(Color.Cyan)
                    ,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    //날짜 텍스트
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        val mondayNum =
                            today.minusDays((today.dayOfWeek.value - DayOfWeek.MONDAY.value).toLong())
                        for (i in 0..6) {
                            val dayText = mondayNum.plusDays(i.toLong())
                                .format(DateTimeFormatter.ofPattern("dd"))

                            Text(
                                text = dayText,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(top = 2.dp)
                                    .onGloballyPositioned {
                                        dateTextHeight = with(density) { it.size.height.toDp() }
                                    },
                                color = Color.Black,
                                fontSize = 12.sp,
                                fontFamily = NanumNeo,
                                fontWeight = FontWeight.Black,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    //스케줄 배치 공간
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.spacedBy(1.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .height(16.dp)
                                .width(90.dp)
                                .background(
                                    color = Color.Magenta,
                                    shape = RoundedCornerShape(3.dp)
                                )
                                .padding(horizontal = 2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "생일카페 113213213213232132132",
                                modifier = Modifier
                                    .wrapContentSize(),
                                fontSize = 8.sp,
                                textAlign = TextAlign.Center,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                            )
                        }

                        Box(
                            modifier = Modifier
                                .height(16.dp)
                                .width(90.dp)
                                .background(
                                    color = Color.Magenta,
                                    shape = RoundedCornerShape(3.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "생일카페 1",
                                modifier = Modifier
                                    .wrapContentSize(),
                                fontSize = 8.sp,
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                            )
                        }

                        Box(
                            modifier = Modifier
                                .height(16.dp)
                                .width(90.dp)
                                .background(
                                    color = Color.Magenta,
                                    shape = RoundedCornerShape(3.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "생일카페 1",
                                modifier = Modifier
                                    .wrapContentSize(),
                                fontSize = 8.sp,
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                            )
                        }

                        Box(
                            modifier = Modifier
                                .height(16.dp)
                                .width(90.dp)
                                .background(
                                    color = Color.Magenta,
                                    shape = RoundedCornerShape(3.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "생일카페 1",
                                modifier = Modifier
                                    .wrapContentSize(),
                                fontSize = 8.sp,
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                            )
                        }
                    }
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
                    .weight(1f)
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