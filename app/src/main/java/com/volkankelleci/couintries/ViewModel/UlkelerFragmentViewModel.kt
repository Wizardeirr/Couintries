package com.volkankelleci.couintries.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.volkankelleci.couintries.Model.Country

class UlkelerFragmentViewModel:ViewModel() {

    val countries= MutableLiveData<List<Country>>()
    val countryError=MutableLiveData<Boolean>()
    val countryLoading=MutableLiveData<Boolean>()

    fun refreshData(){
        val country=Country("Turkey","Angara","Europe","TRY","www.tr.com","Turkish")
        val country2=Country("HOLLANDA","GOTE","Europe","HLD","www.tr.com","Felemenkce")
        val country3=Country("ALMANYA","BERLIN","Europe","EURO","www.berlin.com","Deutsch")

        val countryList= arrayListOf<Country>(country,country2,country3)

        countries.value= countryList
        countryError.value=false
        countryLoading.value=false
    }
}