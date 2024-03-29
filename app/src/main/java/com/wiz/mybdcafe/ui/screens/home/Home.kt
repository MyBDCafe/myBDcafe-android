package com.wiz.mybdcafe.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.NaverMap
import com.wiz.mybdcafe.R
import com.wiz.mybdcafe.ui.components.BottomNavigation
import com.wiz.mybdcafe.ui.components.FAB

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun Home() {
    Scaffold(
        topBar = { AppBarHome() },
        bottomBar = { BottomNavigation() },
        floatingActionButton = { FAB {
            /* TODO: 정보 추가 화면으로 네비게이션 */
        }}
    ) { innerValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerValues)
                .background(colorResource(id = R.color.gray_200))
        ) {
            Filter(groupTitle = "PLAVE") /* TODO */
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                NaverMap()
                WeeklyCalendar()
            }
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    Home()
}