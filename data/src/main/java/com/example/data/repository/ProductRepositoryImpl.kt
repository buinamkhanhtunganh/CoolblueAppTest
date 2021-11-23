package com.example.data.repository

import com.example.data.api.ApiClient
import com.example.domain.entity.ProductData
import com.example.domain.repository.ProductRepository

class ProductRepositoryImpl(): ProductRepository {

    override suspend fun requestData(onResponse: (productData: ProductData) -> Unit, onError: (error: String) -> Unit) {
        val response = ApiClient.instance.getProduct()
        if (response.isSuccessful) {
            response.body()?.let { onResponse(it) }
        } else {
            response.errorBody()?.toString()?.let { onError(it) }
        }
    }
}