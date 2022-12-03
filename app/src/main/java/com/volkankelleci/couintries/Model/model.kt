package com.volkankelleci.couintries.Model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Country(
    @SerializedName("name") val countryName: String?,
    @SerializedName("capital") val countryCapital: String?,
    @SerializedName("region") val countryRegion: String?,
    @SerializedName("currency") val countryCurrency: String?,
    @SerializedName("flag") val imageURL: String?,
    @SerializedName("language") val countryLanguage: String?
)

//Base URL :https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

