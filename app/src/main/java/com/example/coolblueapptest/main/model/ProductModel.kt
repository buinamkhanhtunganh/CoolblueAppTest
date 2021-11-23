package com.example.coolblueapptest.main.model

import com.example.coolblueapptest.di.component.DaggerProductComponent
import com.example.coolblueapptest.di.module.ProductModule
import com.example.coolblueapptest.main.contract.ProductContract
import com.example.coolblueapptest.main.contract.ProductContract.Model.OnFinishedListener
import com.example.domain.usecase.ProductUseCase
import javax.inject.Inject

class ProductModel: ProductContract.Model {

    @Inject
    lateinit var productUseCase: ProductUseCase

    override suspend fun getData(onFinishedListener: OnFinishedListener) {

        DaggerProductComponent.builder().productModule(ProductModule())
            .build().inject(this)

        productUseCase.getData(
            onSuccess = { onFinishedListener.onFinished(it) },
            onFailure = { onFinishedListener.onFailure(it) }
        )
    }
}