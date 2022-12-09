package com.volkankelleci.couintries.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.volkankelleci.couintries.Model.Country

@Dao
interface DAO{

    @Insert()
    suspend fun insertAll(vararg countries:Country):List<Long>

    @Query("SELECT * FROM Country")
    suspend fun getAllCountries():List<Country>

@Query("SELECT * FROM country WHERE uuid= :countryID")
suspend fun getCountry(countryID:Int):Country

@Query("DELETE FROM country")
suspend fun deleteAllCountry()
}
