package com.androidpractice.currencyconverter.di

import com.androidpractice.currencyconverter.data.models.CurrencyApi
import com.androidpractice.currencyconverter.main.DefaultMainRepository
import com.androidpractice.currencyconverter.main.MainRepository
import com.androidpractice.currencyconverter.util.Dispatcherprovider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.*
import javax.inject.Singleton

private const val BASE_URL = "https://api.exchangeratesapi.io/"

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCurrencyApi(): CurrencyApi =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyApi::class.java)

      @Singleton
      @Provides
      fun provideMainRepository(api: CurrencyApi):MainRepository=DefaultMainRepository(api)

    @Singleton
    @Provides
    fun provideDispatchers(): Dispatcherprovider = object : Dispatcherprovider{
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

}