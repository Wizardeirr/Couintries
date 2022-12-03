package com.volkankelleci.couintries.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.volkankelleci.couintries.Model.Country
import com.volkankelleci.couintries.service.CountriesRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class UlkelerFragmentViewModel:ViewModel() {

    val countries= MutableLiveData<List<Country>>()
    val countryError=MutableLiveData<Boolean>()
    val countryLoading=MutableLiveData<Boolean>()

    private val CountryAPIservice= CountriesRetrofit()
    private val disposable= CompositeDisposable()

    fun refreshData(){
        getDataFromAPI()
    }
    private fun getDataFromAPI(){
        countryLoading.value=true

        disposable.add(
            CountryAPIservice.getData().subscribeOn(Schedulers.newThread()).
                    observeOn(AndroidSchedulers.mainThread()).
                    subscribeWith(object:DisposableSingleObserver<List<Country>>(){
                        override fun onSuccess(t: List<Country>) {
                            countries.value=t
                            countryError.value=false
                            countryLoading.value=false
                        }

                        override fun onError(e: Throwable) {
                            countryLoading.value=false
                            countryError.value=true
                            e.printStackTrace()
                        }

                    })
        )



    }
}


