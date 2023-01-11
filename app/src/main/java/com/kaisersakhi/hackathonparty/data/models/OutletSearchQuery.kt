package com.kaisersakhi.hackathonparty.data.models

import com.google.gson.annotations.SerializedName

data class OutletSearchQuery(
    val latitude : Double,
    val longitude : Double,
    @SerializedName("term")
    val searchTerm : String,
    @SerializedName("limit")
    val searchLimit : Int = 20
)
