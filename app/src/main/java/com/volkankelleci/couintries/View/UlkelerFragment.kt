package com.volkankelleci.couintries.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.volkankelleci.couintries.R
import com.volkankelleci.couintries.ViewModel.UlkelerFragmentViewModel
import com.volkankelleci.couintries.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_ulkeler.*

class UlkelerFragment : Fragment() {

    private lateinit var viewModel: UlkelerFragmentViewModel
    private val recyclerAdapterOfCountries = RecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ulkeler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(UlkelerFragmentViewModel::class.java)
        viewModel.refreshData()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerAdapterOfCountries


        swipe_refresh_layout.setOnRefreshListener {
            recyclerView.visibility=View.VISIBLE
            progress_bar.visibility=View.GONE
            error_text.visibility=View.GONE
            viewModel.refreshData()
            swipe_refresh_layout.isRefreshing=false

        }

        observeLiveData()

    }
    fun observeLiveData() {
        viewModel.countries.observe(viewLifecycleOwner, Observer {countries->
            countries?.let {
                recyclerView.visibility = View.VISIBLE
                recyclerAdapterOfCountries.updateCountryList(it)

            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    progress_bar.visibility=View.VISIBLE
                    recyclerView.visibility=View.GONE
                    progress_bar.visibility=View.GONE
                }else{
                    progress_bar.visibility=View.GONE
                }
            }
        })
        viewModel.countryError.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    error_text.visibility=View.VISIBLE

                }else{
                    error_text.visibility=View.GONE
                }
            }
        })
        }
}



