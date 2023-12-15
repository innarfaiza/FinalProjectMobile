package com.d121211021.finalprojectmobile.data.source.remote

import com.d121211021.finalprojectmobile.data.response.GetPlaceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    suspend fun getPlace(
    @Query("key") key: String = "41249929-965ed05871767e5081c2a3b07",
    @Query("q") q: String = "hogwarts",
    @Query("image_type") image_type: String = "photo",
    @Query("pretty") pretty: String = "true"
    ) : GetPlaceResponse
}