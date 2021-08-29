package com.seook.travelapp_hanium.utils


enum class RESPONSE_STATE{
    OKAY,
    FAIL
}

object API{

    const val BASE_URL :String = "http://13.125.233.80:8080/"

    const val CLIENT_ID :String=""

    const val SEARCH_PRODUCT :String = "productList"

    const val SEARCH_CITY :String = "cityList"
}