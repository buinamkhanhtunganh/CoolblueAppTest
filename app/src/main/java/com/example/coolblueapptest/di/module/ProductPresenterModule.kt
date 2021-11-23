package com.example.coolblueapptest.di.module

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import com.example.coolblueapptest.main.contract.ProductContract
import com.example.coolblueapptest.main.presenter.ProductPresenter

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProductPresenterModule(private var context: Context, private var view: ProductContract.View) {

    @Singleton
    @Provides
    fun provideMaterialDialog(): MaterialDialog {
        return MaterialDialog(context)
    }

    @Singleton
    @Provides
    fun providePresenter(): ProductPresenter {
        return ProductPresenter(view)
    }
}