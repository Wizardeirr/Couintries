package com.volkankelleci.couintries.ViewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.volkankelleci.couintries.Model.Country
import com.volkankelleci.couintries.Utility.CustomSharedPreferences
import com.volkankelleci.couintries.service.CountriesRetrofit
import com.volkankelleci.couintries.service.CountryDataBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class UlkelerFragmentViewModel(application: Application):CoroutineBaseViewModel(application) {


    private val CountryAPIservice= CountriesRetrofit()
    private val disposable= CompositeDisposable()
    private var customSharedPreferences=CustomSharedPreferences(getApplication())
    private var refreshTime=10 * 60 * 1000 * 1000 * 1000L

    val countries= MutableLiveData<List<Country>>()
    val countryError=MutableLiveData<Boolean>()
    val countryLoading=MutableLiveData<Boolean>()


    fun refreshData(){
        countryLoading.value=true
        val updateTime=customSharedPreferences.getTime()
        if (updateTime!=null && updateTime !=0L &&System.nanoTime()-updateTime<refreshTime){
            getDataFromSQLite()
        }else{
            getDataFromAPI()
        }

    }
    private fun getDataFromSQLite(){
        launch {
            val countries=CountryDataBase(getApplication()).countryDAO().getAllCountries()
            showCountries(countries)
            Toast.makeText(getApplication(),"CountriesFromSQL",Toast.LENGTH_LONG).show()
        }
    }
    private fun getDataFromAPI(){
        countryLoading.value=true

        disposable.add(
            CountryAPIservice.getData().subscribeOn(Schedulers.newThread()).
                    observeOn(AndroidSchedulers.mainThread()).
                    subscribeWith(object:DisposableSingleObserver<List<Country>>(){
                        override fun onSuccess(t: List<Country>) {
                            storeInSQL(t)
                            Toast.makeText(getApplication(),"CountriesFromAPI",Toast.LENGTH_LONG).show()
                        }

                        override fun onError(e: Throwable) {
                            countryLoading.value=false
                            countryError.value=true
                            e.printStackTrace()
                        }

                    })
        )



    }
    private fun showCountries(countryList:List<Country>){
        countries.value=countryList
        countryError.value=false
        countryLoading.value=false
    }
    private fun storeInSQL(list:List<Country>){

            launch {

                val dao=CountryDataBase(getApplication()).countryDAO() //Dao oluşturdum
                dao.deleteAllCountry() //veritabanında daha önce bir şey varsa sildim
                val uuidListesi=dao.insertAll(*list.toTypedArray()) // internetten aldığım verileri veritabanına ekledim

// o bana geriye 1 id listesi verdi, o ID listesini alıp Modeldeki idlere tek tek eşitledim
                var i = 0
                while (i < list.size){
                    list[i].uuid=uuidListesi[i].toInt()
                    i=i+1
                }
            }
        showCountries(list)

        customSharedPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}


