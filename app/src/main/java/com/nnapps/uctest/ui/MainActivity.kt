package com.nnapps.uctest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.nnapps.uctest.R
import com.nnapps.uctest.databinding.ActivityMainBinding
import com.nnapps.uctest.viewmodel.MainViewModel
import com.usercentrics.sdk.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    lateinit var banner: UsercentricsBanner
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        banner = UsercentricsBanner(this)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Usercentrics.isReady(onSuccess = {
            binding.showConsentBannerBtn.isEnabled = true

        }, onFailure = {
            it.printStackTrace()
        })

        setupListeners()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.readTotalCost.observe(this) {
            binding.score.text = it
        }
    }

    private fun setupListeners() {
        binding.showConsentBannerBtn.setOnClickListener { collectConsent() }
    }

    private fun collectConsent() {
        banner.showSecondLayer(
            callback = ::userResponseHandler
        )
    }

    private fun userResponseHandler(userResponse: UsercentricsConsentUserResponse?) {
        userResponse ?: return
        val cmpData = Usercentrics.instance.getCMPData()
        viewModel.serviceCostCalculator(userResponse, cmpData)
    }
}