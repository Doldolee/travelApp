package com.seook.travelapp_hanium.api2

data class NationOkResponse(
    val id: Int,
    val name: String,
    val picture: String,
    val detail: String,
    val clicked: Int
): NationCheckResponse