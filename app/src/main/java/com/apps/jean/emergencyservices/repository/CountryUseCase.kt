package com.apps.jean.emergencyservices.repository

import android.content.Context
import com.apps.jean.emergencyservices.model.Country
import com.apps.jean.emergencyservices.utils.SingletonHolder
import io.reactivex.Completable


class CountryUseCase private constructor(private val context: Context) {

    companion object : SingletonHolder<CountryUseCase, Context>(::CountryUseCase)

    private val countryDAO by lazy { AppDatabase.getInstance(context).countryDAO() }

    fun addAllCountries(countries: List<Country>): Completable = Completable.fromAction {
        countryDAO.insertAll(countries.toTypedArray())
    }

    fun getAllCountries() = countryDAO.getAll()
}