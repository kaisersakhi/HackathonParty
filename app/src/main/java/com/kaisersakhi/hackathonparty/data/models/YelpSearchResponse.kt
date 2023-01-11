package com.kaisersakhi.hackathonparty.data.models

data class YelpSearchResponse(
    val responseText : String = "success",
    val outlets : List<BusinessOutlet>
)
