package com.volkankelleci.couintries.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.volkankelleci.couintries.Model.Country
import com.volkankelleci.couintries.Model.UlkelerRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

class UlkelerFragmentViewModel:ViewModel() {

    val countries= MutableLiveData<List<Country>>()
    val countryError=MutableLiveData<Boolean>()
    val countryLoading=MutableLiveData<Boolean>()
    private val ulkelerAPIservice=UlkelerRetrofit()
    private val disposable= CompositeDisposable()
    fun refreshData(){
        takesToDataFromInternet()
    }
    private fun takesToDataFromInternet(){


        disposable.add(
            ulkelerAPIservice.getdata()
                .subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(t: List<Country>) {
                        countries.value=t
                        countryError.value=false
                        countryLoading.value=false
                    }
                    override fun onError(e: Throwable) {
                        countryError.value=true
                        countryLoading.value=false
                        e.printStackTrace()
                    }
                })
        )
    }
}


