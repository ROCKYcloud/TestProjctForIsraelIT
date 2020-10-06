package com.example.testprojectforwork.api

import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface BierService {
    @GET("beers")
    fun getBeers() : Observable<List<BeerResponse>>
    @GET("beers/{id}")
    fun getBeer(@Path("id") id:Long) : Observable<List<BeerResponse>>
}