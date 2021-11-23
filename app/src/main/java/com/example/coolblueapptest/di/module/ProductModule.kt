package com.example.coolblueapptest.di.module

import com.example.data.repository.ProductRepositoryImpl
import com.example.domain.usecase.ProductUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProductModule() {

    @Singleton
    @Provides
    fun provideProductUseCase(): ProductUseCase {
        return ProductUseCase(ProductRepositoryImpl())
    }
}