package com.volkankelleci.couintries.Model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Country(
    val countryName: String?,
    val countryCapital: String?,
    val countryRegion: String?,
    val countryCurrency: String?,
    val imageURL: String?,
    val countryLanguage: String?
)

//Base URL :https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

