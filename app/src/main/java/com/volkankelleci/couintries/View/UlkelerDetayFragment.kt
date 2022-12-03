package com.volkankelleci.couintries.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.volkankelleci.couintries.Model.Country
import com.volkankelleci.couintries.R
import com.volkankelleci.couintries.ViewModel.UlkelerDetayFragmentViewModel
import kotlinx.android.synthetic.main.fragment_ulkeler_detay.*

class UlkelerDetayFragment : Fragment() {
    private lateinit var ulkelerDetayViewModel:UlkelerDetayFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ulkeler_detay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ulkelerDetayViewModel=ViewModelProviders.of(this).get(UlkelerDetayFragmentViewModel::class.java)
        ulkelerDetayViewModel.verileriRoomdanAl()
        observeLiveDataOnDetay()
    }
    fun observeLiveDataOnDetay(){
        ulkelerDetayViewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                name_text.text=it.countryName
                region_text.text=it.countryRegion
                currency_text.text=it.countryCurrency
                language_text.text=it.countryLanguage
                capital_text.text=it.countryCapital
            }
        })

    }

}