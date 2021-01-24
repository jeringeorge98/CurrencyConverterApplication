package com.androidpractice.currencyconverter.util

sealed class Resource<T>(val data :T?,val error:String?) {

    class Success<T>(data: T?): Resource<T>(data,null)

    class Error<T>(message:String): Resource<T>(null,message)


}