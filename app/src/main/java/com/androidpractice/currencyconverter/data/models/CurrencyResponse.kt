package com.androidpractice.currencyconverter.data.models

data class CurrencyResponse(
    val base: String,
    val date: String,
    val rates: Rates
)