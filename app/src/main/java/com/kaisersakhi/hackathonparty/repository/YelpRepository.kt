package com.kaisersakhi.hackathonparty.repository

import com.kaisersakhi.hackathonparty.data.api.IYelpAPI
import com.kaisersakhi.hackathonparty.data.models.OutletSearchQuery
import com.kaisersakhi.hackathonparty.data.models.YelpSearchResponse
import javax.inject.Inject

class YelpRepository @Inject constructor(private val api : IYelpAPI){

    suspend fun getOutlets(
        outletSearchQuery: OutletSearchQuery,
        onResponse: (YelpSearchResponse?) -> Unit
    ) {
        val response = api.getOutlets(
            longitude = outletSearchQuery.longitude,
            latitude = outletSearchQuery.latitude,
            searchTerm = outletSearchQuery.searchTerm
        )
        onResponse.invoke(response)
    }
}