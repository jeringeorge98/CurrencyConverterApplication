package com.androidpractice.currencyconverter.main

import com.androidpractice.currencyconverter.data.models.CurrencyResponse
import com.androidpractice.currencyconverter.util.Resource

interface MainRepository {

  suspend fun getRates(base:String):Resource<CurrencyResponse>



}