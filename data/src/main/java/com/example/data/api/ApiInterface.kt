package com.example.data.api

import com.example.domain.entity.ProductData
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("mobile-assignment/search?que")
    suspend fun getProduct(): Response<ProductData>
}