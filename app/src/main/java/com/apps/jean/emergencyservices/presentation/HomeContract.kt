package com.apps.jean.emergencyservices.presentation

import android.content.Context
import android.content.res.AssetManager
import com.apps.jean.emergencyservices.model.Country

class HomeContract {

    interface View {

        fun showLoading()

        fun hideLoading()

        fun onDbInitialized()

        fun setCountries(countries: List<Country>)
    }

    interface Presenter {
        fun attach(view: View)

        fun initDb(context: Context, assetManager: AssetManager)

        fun load(context: Context)
    }
}