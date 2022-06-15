package com.example.demoapp.adapter

import android.app.Activity
import android.content.Context
import android.icu.number.NumberFormatter.with
import android.net.Uri
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoapp.R
import com.example.demoapp.mod.Country
import kotlinx.android.synthetic.main.item_layout.view.*
import java.util.Collections.addAll

class CountryAdapter(private val countries: ArrayList<Country>, private val activity: Activity) : RecyclerView.Adapter<CountryAdapter.DataViewHolder>() {

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(country: Country) {
            itemView.apply {
                tv_name.text = "Name - ${country.name}"
                tv_capital.text = "Capital - ${country.capital}"
                tv_region.text = "Region - ${country.region}"
                tv_subregion.text = "Subregion - ${country.subregion}"
                tv_population.text = "Population - ${country.population}"
                Glide.with(activity).load(country.flags.png).into(iv_image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    fun addCountries(countries: ArrayList<Country>){
        this.countries.apply {
            clear()
            addAll(countries)
        }
    }
}