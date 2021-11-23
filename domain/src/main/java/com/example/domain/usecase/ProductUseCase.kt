package com.example.domain.usecase

import com.example.domain.entity.ProductData
import com.example.domain.repository.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductUseCase @Inject constructor(private var productRepository: ProductRepository) {

    suspend fun getData(onSuccess: (productData: ProductData) -> Unit, onFailure: (error: String) -> Unit) {
        productRepository.requestData(onResponse = onSuccess, onError = onFailure)
    }
}