package com.volkankelleci.couintries.service

import com.volkankelleci.couintries.Model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountriesRetrofit {
    // BASE URL = https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json
    private val BASE_URL="https://raw.githubusercontent.com/"
    private val api=Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(RetrofitInterface::class.java)
    fun getData():Single<List<Country>>{
        return api.ulkeleriAl()
    }
}
