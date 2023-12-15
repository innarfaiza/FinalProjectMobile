package com.d121211021.finalprojectmobile.data.response

import com.d121211021.finalprojectmobile.data.models.Place
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPlaceResponse(
    @SerialName("hits")
    val hits: List<Place>,
    @SerialName("total")
    val total: Int,
    @SerialName("totalHits")
    val totalHits: Int
)