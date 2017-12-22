package com.apps.jean.emergencyservices.presentation

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.apps.jean.emergencyservices.R
import com.apps.jean.emergencyservices.model.Country
import com.apps.jean.emergencyservices.utils.writeToSharedPreferences
import kotlinx.android.synthetic.main.main_container_activity.*

class Home : AppCompatActivity(), HomeContract.View, AdapterView.OnItemSelectedListener {

    companion object {
        private val DB_INIT = "DB_INIT"
        private val SELECTED_COUNTRY = "SELECTED_COUNTRY"
    }

    private val sharedPref by lazy { getPreferences(Context.MODE_PRIVATE) }
    private val dbInit by lazy { sharedPref.getBoolean(DB_INIT, false) }

    private val presenter = HomePresenter()
    private var countries: List<Country>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_container_activity)

        presenter.attach(this)

        setSupportActionBar(toolbar)
        init()
    }

    private fun init() {
        spinner.onItemSelectedListener = this

        if (!dbInit) {
            presenter.initDb(this, assets)
        }

        presenter.load(this)
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun onDbInitialized() {
        writeToSharedPreferences { it.putBoolean(DB_INIT, true) }
    }

    override fun setCountries(countries: List<Country>) {
        this.countries = countries
        val arrayOfCountries = ArrayList<String>()
        arrayOfCountries.addAll(countries.map { it.name })

        val dataAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayOfCountries)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = dataAdapter
        setSelection()
    }

    private fun setSelection() {
        countries?.let {
            sharedPref.getString(SELECTED_COUNTRY, null)?.let { countryName ->
                spinner.setSelection(it.indexOfFirst { it.name == countryName })
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) = Unit

    override fun onItemSelected(adapterView: AdapterView<*>, view: View, index: Int, id: Long) {
        countries?.let {
            val countryName = adapterView.adapter.getItem(index) as String
            val country = it.single { it.name == countryName }

            main.text = getString(R.string.number_main, country.main)
            police.text = getString(R.string.number_police, country.police)
            ambulance.text = getString(R.string.number_ambulance, country.ambulance)
            fire.text = getString(R.string.number_fire, country.fire)

            writeToSharedPreferences { it.putString(SELECTED_COUNTRY, countryName) }
        }
    }
}
