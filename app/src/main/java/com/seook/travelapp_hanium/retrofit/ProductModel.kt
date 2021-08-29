package com.seook.travelapp_hanium.retrofit

data class ProductModel (
    val id:Int,
    val name:String ="",
    val picture:String="",
    val price:Int,
    val clicked: Int
)

data class CityModel (
    val id:Int,
    val name:String ="",
    val picture:String="",
    val detail:String="",
    val clicked: Int
)
