package com.volkankelleci.couintries.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.volkankelleci.couintries.Model.Country

@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDataBase:RoomDatabase() {
    abstract fun countryDAO():DAO

    companion object{
        //Volatile diğer tredlere de görünür yapar.
        @Volatile private var instance:CountryDataBase?=null
        private val lock=Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance?: makeDataBase(context).also {
                instance=it
            }
        }
        //invoke = ateşlemek başlatmak , senkronize bir şekilde instance oluşturulduysa onu döndür,
        //eğer oluşturulmadıysa senkronize yap.
        //instance?: = Elvis Operatürü.
        private fun makeDataBase(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            CountryDataBase::class.java,"countrydatabase").build()
    }
}