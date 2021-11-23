package com.example.coolblueapptest.di.component


import com.example.coolblueapptest.di.module.ProductModule
import com.example.coolblueapptest.main.model.ProductModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ProductModule::class])
interface ProductComponent {

    fun inject(model: ProductModel)
}