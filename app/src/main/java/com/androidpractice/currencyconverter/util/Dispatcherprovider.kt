package com.androidpractice.currencyconverter.util

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatcherprovider {

    val main :CoroutineDispatcher
    val io : CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined:CoroutineDispatcher
}