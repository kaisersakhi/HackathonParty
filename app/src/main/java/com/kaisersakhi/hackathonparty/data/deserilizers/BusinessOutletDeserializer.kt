package com.kaisersakhi.hackathonparty.data.deserilizers

import com.google.gson.*
import com.kaisersakhi.hackathonparty.data.models.BusinessLocation
import com.kaisersakhi.hackathonparty.data.models.BusinessOutlet
import com.kaisersakhi.hackathonparty.data.models.YelpSearchResponse
import java.lang.reflect.Type

class BusinessOutletDeserializer : JsonDeserializer<YelpSearchResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): YelpSearchResponse {

        val businesses = json?.asJsonObject?.get("businesses") as JsonArray
        if (businesses.isJsonNull){
            return YelpSearchResponse("failure", emptyList())
        }
        val outlets = mutableListOf<BusinessOutlet>()

        for (business in businesses) {

            val location = (business as JsonObject).get("location").asJsonObject
            val coordinates = business.get("coordinates").asJsonObject
            val businessLocation = BusinessLocation(
                address = if (location.get("address1") == JsonNull.INSTANCE) "" else location.get("address1").asString,
                city = if (location.get("city") == JsonNull.INSTANCE) "" else location.get("city").asString,
                zipCode = if (location.get("zip_code") == JsonNull.INSTANCE) "" else location.get("zip_code").asString,
                state = if (location.get("state") == JsonNull.INSTANCE) "" else location.get("state").asString,
            )

            outlets.add(
                BusinessOutlet(
                    id = business.get("id").asString,
                    name = business.get("name").asString,
                    imageUrl = business.get("image_url").asString,
                    reviewCount = business.get("review_count").asInt,
                    rating = business.get("rating").asFloat,
                    distance = business.get("distance").asDouble,
                    location = businessLocation,
                    longitudeLatitude = "${coordinates.get("latitude")},${coordinates.get("longitude")}"
                )
            )
        }
        return YelpSearchResponse(outlets = outlets)
    }
}