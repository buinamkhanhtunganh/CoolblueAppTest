package com.example.coolblueapptest.di.component

import com.example.coolblueapptest.di.module.ProductPresenterModule
import com.example.coolblueapptest.main.view.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ProductPresenterModule::class])
interface ProductPresenterComponent {

    fun inject(fragment: MainActivity)
}