package com.d121211021.finalprojectmobile.data.repository

import com.d121211021.finalprojectmobile.data.response.GetPlaceResponse
import com.d121211021.finalprojectmobile.data.source.remote.ApiService

class PlaceRepository (private val apiService: ApiService) {

    suspend fun getPlace(): GetPlaceResponse {
        return apiService.getPlace()
    }
}