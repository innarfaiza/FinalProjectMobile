package com.d121211021.finalprojectmobile.data

import com.d121211021.finalprojectmobile.data.repository.PlaceRepository
import com.d121211021.finalprojectmobile.data.source.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val placeRepository: PlaceRepository
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL = "https://pixabay.com"

//    val json = Json {
//        ignoreUnknownKeys = true
//    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val placeRepository: PlaceRepository
        get() = PlaceRepository(retrofitService)

}