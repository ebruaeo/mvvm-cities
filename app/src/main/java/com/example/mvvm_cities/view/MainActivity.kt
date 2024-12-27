package com.example.mvvm_cities.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_cities.R
import com.example.mvvm_cities.databinding.ActivityMainBinding
import com.example.mvvm_cities.viewmodel.CityViewModel

class MainActivity : AppCompatActivity() {

    //   private val viewModel: CityViewModel by viewModels()
    private lateinit var viewModel: CityViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(CityViewModel::class.java)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    override fun onResume() {
        super.onResume()

        viewModel.getCityData().observe(this, Observer { city ->
            binding.cityImage.setImageDrawable(
                ResourcesCompat.getDrawable(resources, city.image, applicationContext.theme)
            )
            binding.cityNameTV.text = city.name
            binding.cityPopulationTV.text = city.population.toString()
        })
    }
}