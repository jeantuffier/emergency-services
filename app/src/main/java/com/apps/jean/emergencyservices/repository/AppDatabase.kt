package com.apps.jean.emergencyservices.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.apps.jean.emergencyservices.model.Country
import com.apps.jean.emergencyservices.utils.SingletonHolder

@Database(entities = arrayOf(Country::class), version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun countryDAO() : CountryDAO

    companion object : SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, AppDatabase::class.java, "emergency_services_db")
                .build()
    })
}