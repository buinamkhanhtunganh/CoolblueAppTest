package com.example.coolblueapptest.main.presenter

import com.example.coolblueapptest.main.contract.ProductContract
import com.example.coolblueapptest.main.model.ProductModel
import com.example.domain.entity.ProductData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductPresenter @Inject constructor(userView: ProductContract.View) : ProductContract.Presenter, ProductContract.Model.OnFinishedListener {
    private var view: ProductContract.View? = userView
    private var model: ProductContract.Model = ProductModel()

    override fun onDestroy() {
        view = null
    }

    override suspend fun requestDataFromServer() {
        view?.showProgress()
        model.getData(this)
    }

    override fun onFinished(productData: ProductData) {
        view?.updateView(productData)
        view?.hideProgress()
    }

    override fun onFailure(error: String) {
        view?.onResponseFailure(error)
    }
}