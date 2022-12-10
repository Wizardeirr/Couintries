package com.volkankelleci.couintries.ViewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.volkankelleci.couintries.Model.Country
import com.volkankelleci.couintries.service.CountryDataBase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.UUID

class UlkelerDetayFragmentViewModel(application: Application):CoroutineBaseViewModel(application) {
    val countryLiveData=MutableLiveData<Country>()

    fun getDataFromRoom(uuid: Int){
        launch {

            val dao=CountryDataBase(getApplication()).countryDAO()
            val country=dao.getCountry(uuid)
            countryLiveData.value=country
        }

    }

}