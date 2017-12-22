package com.apps.jean.emergencyservices.repository

import android.arch.persistence.room.*
import com.apps.jean.emergencyservices.model.Country
import io.reactivex.Flowable

@Dao
interface CountryDAO {

    @Query("SELECT * FROM country")
    fun getAll(): Flowable<List<Country>>

    @Query("SELECT * FROM country WHERE id LIKE :id LIMIT 1")
    fun getById(id: Int): Flowable<Country>

    @Query("SELECT * from country WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Flowable<List<Country>>

    @Insert
    fun insertAll(countries: Array<Country>)

    @Delete
    fun delete(country: Country)

    @Update
    fun update(country: Country)
}