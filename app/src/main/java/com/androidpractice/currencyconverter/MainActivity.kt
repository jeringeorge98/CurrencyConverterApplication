package com.androidpractice.currencyconverter

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.androidpractice.currencyconverter.databinding.ActivityMainBinding
import com.androidpractice.currencyconverter.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding
    private val viewModel :MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    binding.btnConvert.setOnClickListener {
        viewModel.convert(binding.etFrom.text.toString(),binding.spFromCurrency.selectedItem.toString(),binding.spToCurrency.selectedItem.toString())
    }
        lifecycleScope.launchWhenStarted {
          viewModel.conversion.collect{event ->
              when(event){
                 is MainViewModel.CurrencyEvent.Sucess ->{
                 binding.loadingBar.isVisible=false
                     binding.tvResult.setTextColor(Color.BLACK)
                     binding.tvResult.text =event.resultText
                 }
                  is MainViewModel.CurrencyEvent.Failure ->{
                  binding.loadingBar.isVisible =false
                  binding.tvResult.setTextColor(Color.RED)
                  binding.tvResult.text =event.errorText
                  }
                  is MainViewModel.CurrencyEvent.Loading ->{
                      binding.loadingBar.isVisible =true
                  }
                  else -> Unit
              }

          }
        }
    }
}