package com.example.webapi.Retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitHelper {
    private const val url="http://109.188.103.206:11221/UT11_Demo/hs/MobileBC/"
    private val retrofit:Retrofit=Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(ScalarsConverterFactory.create())
        //.addConverterFactory(SimpleXmlConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun <T> useTable(obj:Class<T>):T {
        return retrofit.create(obj) as T
    }
}