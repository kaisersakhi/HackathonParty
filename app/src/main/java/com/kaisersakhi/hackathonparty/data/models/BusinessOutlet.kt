package com.kaisersakhi.hackathonparty.data.models

data class BusinessOutlet(
    val id : String,
    val name : String,
    val imageUrl : String,
    val reviewCount : Int,
    val rating : Float,
    val distance : Double,
    val longitudeLatitude : String,
    val location : BusinessLocation
)
