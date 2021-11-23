package com.example.coolblueapptest.main.contract

import com.example.domain.entity.ProductData

interface ProductContract {
    interface Model {
        interface OnFinishedListener {
            fun onFinished(productData: ProductData)
            fun onFailure(error: String)
        }
        suspend fun getData(onFinishedListener: OnFinishedListener)
    }

    interface View {
        fun showProgress()
        fun hideProgress()
        fun updateView(productData: ProductData)
        fun onResponseFailure(error: String)
    }

    interface Presenter {
        fun onDestroy()
        suspend fun requestDataFromServer()
    }
}