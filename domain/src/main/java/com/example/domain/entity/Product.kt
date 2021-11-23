package com.example.domain.entity

import java.io.Serializable

data class Product(
    val productId: Int,
    val productName: String,
    val productImage: String,
) : Serializable