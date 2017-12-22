package com.apps.jean.emergencyservices.presentation

import android.content.Context
import android.content.res.AssetManager
import com.apps.jean.emergencyservices.model.Country
import com.apps.jean.emergencyservices.repository.CountryUseCase
import com.apps.jean.emergencyservices.utils.read
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter : HomeContract.Presenter {

    companion object {
        private val FILE_NAME = "countries.json"
    }

    private lateinit var view: HomeContract.View

    override fun attach(view: HomeContract.View) {
        this.view = view
    }

    override fun initDb(context: Context, assetManager: AssetManager) {
        val json = assetManager.read(FILE_NAME)
        val countries = Gson().fromJson<List<Country>>(json)

        CountryUseCase.getInstance(context).addAllCountries(countries)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { view.onDbInitialized() }
    }

    override fun load(context: Context) {
        CountryUseCase.getInstance(context).getAllCountries()
                .doOnSubscribe { view.showLoading() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    view.setCountries(it)
                    view.hideLoading()
                }
    }
}
