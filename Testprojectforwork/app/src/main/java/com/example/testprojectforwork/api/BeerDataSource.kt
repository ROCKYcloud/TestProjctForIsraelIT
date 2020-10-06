package com.example.testprojectforwork.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable

class BeerDataSource {

    private val URL = "https://api.punkapi.com/v2/"

    private val json by lazy {
        GsonBuilder()
            .setLenient()
            .create()
    }

    private val okHttpClient by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(json))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private val apiService: BierService by lazy {
        retrofit.create(BierService::class.java)
    }

    fun getBeer(id:Long): Observable<List<BeerResponse>> {
        return apiService.getBeer(id)
    }
}