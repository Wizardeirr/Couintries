package com.volkankelleci.couintries.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country(
    @ColumnInfo(name="name") @SerializedName("name") val countryName: String?,
    @ColumnInfo(name="capital") @SerializedName("capital") val countryCapital: String?,
    @ColumnInfo(name="region") @SerializedName("region") val countryRegion: String?,
    @ColumnInfo(name="currency")  @SerializedName("currency") val countryCurrency: String?,
    @ColumnInfo(name="flag")   @SerializedName("flag") val imageURL: String?,
    @ColumnInfo(name="language")    @SerializedName("language") val countryLanguage: String?
){
    @PrimaryKey (autoGenerate = true)
    val uuid:Int=0

}


//Base URL :https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

