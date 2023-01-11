package com.kaisersakhi.hackathonparty.data.api

import com.kaisersakhi.hackathonparty.data.models.YelpSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IYelpAPI {
    @GET("businesses/search")
    suspend fun getOutlets(
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude : Double,
        @Query("term") searchTerm : String,
        @Query("limit") limit : Int = 20,
        @Query("radius") radius : Int = 2000 //1km
    ): YelpSearchResponse
}