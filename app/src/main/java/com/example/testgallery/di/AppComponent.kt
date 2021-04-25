package com.example.testgallery.di

import com.example.testgallery.data.api.ApiService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, ApiServiceModule::class])
interface AppComponent {
    fun provideApi(): ApiService
}