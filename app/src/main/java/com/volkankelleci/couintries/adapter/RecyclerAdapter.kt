package com.volkankelleci.couintries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.volkankelleci.couintries.Model.Country
import com.volkankelleci.couintries.R
import kotlinx.android.synthetic.main.fragment_ulkeler_detay.view.*
import kotlinx.android.synthetic.main.recycler_gorunumu.view.*

class RecyclerAdapter(val CountryList: ArrayList<Country>) :
    RecyclerView.Adapter<RecyclerAdapter.CountryViewHolder>() {
    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_gorunumu, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.itemView.countrynameinrecycler.text = CountryList.get(position).countryName
        holder.itemView.regionnameinrecycler.text = CountryList.get(position).countryRegion


        //Görsel Eklenecek
    }

    override fun getItemCount(): Int {
        return CountryList.size
    }
    fun updateCountryList(newCountryList:List<Country>){
        CountryList.clear()
        CountryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}