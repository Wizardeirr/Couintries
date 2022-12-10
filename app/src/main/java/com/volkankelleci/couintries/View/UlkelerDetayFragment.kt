package com.volkankelleci.couintries.View

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.volkankelleci.couintries.R
import com.volkankelleci.couintries.Utility.glideileResimAl
import com.volkankelleci.couintries.ViewModel.UlkelerDetayFragmentViewModel
import kotlinx.android.synthetic.main.fragment_ulkeler_detay.*

class UlkelerDetayFragment : Fragment() {
    private lateinit var ulkelerDetayViewModel:UlkelerDetayFragmentViewModel
    private var countryUUID=0

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
        arguments?.let {
            countryUUID=UlkelerDetayFragmentArgs.fromBundle(it).uuid
        }
        ulkelerDetayViewModel=ViewModelProviders.of(this).get(UlkelerDetayFragmentViewModel::class.java)
        ulkelerDetayViewModel.getDataFromRoom(countryUUID)
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
                context?.let { it1 -> CircularProgressDrawable(it1) }?.let { it2 ->
                    countryImage.glideileResimAl(it.imageURL.toString(),
                        it2)
                }


            }
        })

    }

}