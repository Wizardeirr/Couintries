package com.volkankelleci.couintries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.volkankelleci.couintries.Model.Country
import com.volkankelleci.couintries.R
import com.volkankelleci.couintries.Utility.glideileResimAl
import com.volkankelleci.couintries.Utility.placeHolderYap
import com.volkankelleci.couintries.View.UlkelerFragmentDirections
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
        holder.itemView.setOnClickListener{
            val action=UlkelerFragmentDirections.actionUlkelerFragmentToUlkelerDetayFragment(CountryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.itemView.countryimageinrecycler.glideileResimAl(CountryList[position].imageURL.toString(),
            placeHolderYap(holder.itemView.context))




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