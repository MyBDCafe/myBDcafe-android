package com.wiz.mybdcafe.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.util.Date

/*
* 이벤트 리퀘스트
*/

sealed class EventRequest {
    data class Create(
        val eventName: String,
        val mainCharacter: String? = null,
        val genre: String? = null,
        val startDate: Date? = null,
        val endDate: Date? = null,
        val location: Location.Basic,
        val businessHours: List<BusinessHour.Basic> = listOf(),
        val eventUrl: String? = null,
        val memo: String? = null
    )
    data class Update(
        val eventName: String,
        val mainCharacter: String? = null,
        val genre: String? = null,
        val startDate: Date? = null,
        val endDate: Date? = null,
        val location: Location.ID,
        val businessHours: List<BusinessHour.ID> = listOf(),
        val eventUrl: String? = null,
        val memo: String? = null
    )
}

/*
* 이벤트 리스폰스
*/

sealed class EventResponse {
    data class Status(
        val status: Int,
        val timestamp: LocalDateTime,
        val errorClass: String,

        @SerializedName(value = "errMsg")
        val errorMessage: String
    )

    data class Data(
        val content: List<Event> = listOf(),
        val count: Long,
        val size: Int,
        val page: Int
    )
}

data class Event(
    val eventId: Long,
    val eventName: String,
    val mainCharacter: String? = null,
    val genre: String? = null,
    val startDate: Date? = null,
    val endDate: Date? = null,
    val location: Location.Basic,
    val businessHours: List<BusinessHour.Basic> = listOf(),
    val eventUrl: String? = null,
    val memo: String? = null
)

sealed class Location {
    data class Basic(
        val latitude: String? = null,
        val longitude: String? = null
    )

    data class ID(
        val locationId: Long,
        val latitude: String? = null,
        val longitude: String? = null
    )
}

sealed class BusinessHour {
    data class Basic(
        val day: Date? = null,
        val openTime: Date? = null,
        val closeTime: Date? = null
    )

    data class ID(
        val hourId: Long,
        val day: Date? = null,
        val openTime: Date? = null,
        val closeTime: Date? = null
    )
}