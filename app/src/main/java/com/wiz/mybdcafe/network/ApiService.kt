package com.wiz.mybdcafe.network

import com.wiz.mybdcafe.models.EventRequest
import com.wiz.mybdcafe.models.EventResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Date

interface ApiService {
    //이벤트 통합 검색
    @GET("event/search")
    suspend fun searchEvent(
        @Query("g") genre: String? = null,
        @Query("c") mainCharacter: String? = null,
        @Query("s") startDate: Date? = null,
        @Query("e") endDate: Date? = null
    ): EventResponse.Data

    //이벤트 ID로 검색
    @GET("/getevent/{id}")
    suspend fun searchEventById(@Path("id") id: Long): EventResponse.Data

    //이벤트 생성
    @POST("event/create")
    suspend fun createEvent(@Body event: EventRequest.Create): EventResponse.Status

    //이벤트 수정
    @PATCH("event/update")
    suspend fun updateEvent(@Body event: EventRequest.Update): EventResponse.Status

    //이벤트 삭제
    @PATCH("event/delete/{eventId}")
    suspend fun deleteEvent(@Path("eventId") eventId: Long): EventResponse.Status
}