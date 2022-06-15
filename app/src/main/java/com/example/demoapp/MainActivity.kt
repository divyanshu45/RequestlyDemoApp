package com.example.demoapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.api.ApiHelper
import com.example.countries.api.RetrofitInstance
import com.example.countries.utils.Status
import com.example.countries.viewmodels.CountryViewModel
import com.example.countries.viewmodels.ViewModelFactory
import com.example.demoapp.adapter.CountryAdapter
import com.example.demoapp.mod.Country
import com.example.demoapp.repository.CountryRepository
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CountryViewModel
    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Countries App"
        setupViewModel()
        setupUI()
        setupObservers()
        btn_retry.setOnClickListener {
            setupObservers()
        }
    }

    private fun setupViewModel() {
        val countryRepository = CountryRepository(ApiHelper(RetrofitInstance.getInstance(this)))
        val viewModelProviderFactory = ViewModelFactory(application, countryRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(CountryViewModel::class.java)
    }

    private fun setupUI() {
        rv_countires.layoutManager = LinearLayoutManager(this)
        adapter = CountryAdapter(arrayListOf(), this)
        rv_countires.addItemDecoration(
            DividerItemDecoration(
                rv_countires.context,
                (rv_countires.layoutManager as LinearLayoutManager).orientation
            )
        )
        rv_countires.adapter = adapter
    }

    private fun setupObservers() {
        chip_group.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            when(chip.id){
                chip_asia.id -> getAsian()
                chip_africa.id -> getAfrican()
                chip_america.id -> getAmerican()
                chip_europe.id -> getEuropean()
            }
        }
        getAsian()

    }

    private fun getAsian(){
        viewModel.getAsianCountries().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        rv_countires.visibility = View.VISIBLE
                        btn_retry.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        resource.data?.let { it -> retrieveList(it)
                            println(it.toString())
                        }
                    }
                    Status.ERROR -> {
                        rv_countires.visibility = View.VISIBLE
                        btn_retry.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, "Check Your Connection", Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        btn_retry.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                        rv_countires.visibility = View.GONE
                    }
                }
            }
        })
        Toast.makeText(this, "Displaying Asian Countries", Toast.LENGTH_SHORT).show()
    }

    private fun getAmerican(){
        viewModel.getAmericanCountries().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        rv_countires.visibility = View.VISIBLE
                        btn_retry.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        resource.data?.let { it -> retrieveList(it) }
                    }
                    Status.ERROR -> {
                        rv_countires.visibility = View.VISIBLE
                        btn_retry.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, "Check Your Connection", Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        btn_retry.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                        rv_countires.visibility = View.GONE
                    }
                }
            }
        })
        Toast.makeText(this, "Displaying American Countries", Toast.LENGTH_SHORT).show()
    }

    private fun getEuropean(){
        viewModel.getEuropeanCountries().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        rv_countires.visibility = View.VISIBLE
                        btn_retry.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        resource.data?.let { it -> retrieveList(it) }
                    }
                    Status.ERROR -> {
                        rv_countires.visibility = View.VISIBLE
                        btn_retry.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, "Check Your Connection", Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        btn_retry.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                        rv_countires.visibility = View.GONE
                    }
                }
            }
        })
        Toast.makeText(this, "Displaying European Countries", Toast.LENGTH_SHORT).show()
    }

    private fun getAfrican(){
        viewModel.getAfricanCountries().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        rv_countires.visibility = View.VISIBLE
                        btn_retry.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        resource.data?.let { it -> retrieveList(it) }
                    }
                    Status.ERROR -> {
                        rv_countires.visibility = View.VISIBLE
                        btn_retry.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, "Check Your Connection", Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        btn_retry.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                        rv_countires.visibility = View.GONE
                    }
                }
            }
        })
        Toast.makeText(this, "Displaying African Countries", Toast.LENGTH_SHORT).show()
    }

    private fun retrieveList(users: ArrayList<Country>) {
        adapter.apply {
            addCountries(users)
            notifyDataSetChanged()
        }
    }
}