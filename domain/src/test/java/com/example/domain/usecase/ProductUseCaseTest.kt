package com.example.domain.usecase

import com.example.domain.getProducts
import com.example.domain.repository.ProductRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductUseCaseTest {

    private lateinit var productUseCase: ProductUseCase

    @Mock
    private lateinit var productRepository: ProductRepository

    @Before
    fun setUp() {
        productUseCase = ProductUseCase(productRepository)
    }

    @Test
    fun getDataSuccess() {
        runBlocking {
            productUseCase.getData(
                onSuccess = {
                    assertEquals(getProducts(), it.products)
                },
                onFailure = {})
        }
    }
}