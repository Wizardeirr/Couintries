package com.volkankelleci.couintries.service

import com.volkankelleci.couintries.Model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitInterface {
    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun ulkeleriAl() : Single<List<Country>>
}