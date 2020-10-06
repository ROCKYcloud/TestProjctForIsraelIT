package com.example.testprojectforwork.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testprojectforwork.api.BeerDataSource
import com.example.testprojectforwork.api.BeerResponse
import com.example.testprojectforwork.api.BeersDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

internal class DeleteViewModel : ViewModel() {

    var beer = MutableLiveData<List<BeerResponse>>()
    fun getBeer(id: Long) {
        BeerDataSource().getBeer(id)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {beerResponse ->
                beer.value = beerResponse
            }
            .subscribe({ onRetrievePostListSuccess() },
                { onRetrievePostListError() })

    }

    private fun onRetrievePostListSuccess() {
        print("success")
    }

    private fun onRetrievePostListError() {
        print("error")
    }

}