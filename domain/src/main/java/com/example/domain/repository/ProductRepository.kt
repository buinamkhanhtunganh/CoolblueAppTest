package com.example.domain.repository

import com.example.domain.entity.ProductData

interface ProductRepository {

    suspend fun requestData(onResponse: (productData: ProductData) -> Unit, onError: (error: String) -> Unit)
}