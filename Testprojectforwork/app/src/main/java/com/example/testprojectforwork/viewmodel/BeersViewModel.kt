package com.example.testprojectforwork.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testprojectforwork.api.BeersDataSource
import com.example.testprojectforwork.api.BeerResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BeersViewModel : ViewModel() {

    init {
        getBeers()
    }
    var beers = MutableLiveData<MutableList<BeerResponse>>()

     fun getBeers() {
            BeersDataSource().getBeers()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                beers.value = it.toMutableList()
                onRetrievePostListSuccess()},
                { onRetrievePostListError() })

    }

    private fun onRetrievePostListSuccess() {
        print("success")
    }

    private fun onRetrievePostListError() {
        print("error")
    }
}