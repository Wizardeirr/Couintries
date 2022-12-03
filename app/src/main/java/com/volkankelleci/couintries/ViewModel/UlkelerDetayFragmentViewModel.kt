package com.volkankelleci.couintries.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.volkankelleci.couintries.Model.Country

class UlkelerDetayFragmentViewModel:ViewModel() {
    val countryLiveData=MutableLiveData<Country>()

    fun verileriRoomdanAl(){
        val simple=Country("Volkan","Kelleci","LO","TRY","EWQ","Elf Dili")
        countryLiveData.value=simple
    }

}