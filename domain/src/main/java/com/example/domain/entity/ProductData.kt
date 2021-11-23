package com.example.domain.entity

import java.io.Serializable

data class ProductData(
    val products: List<Product>,
) : Serializable