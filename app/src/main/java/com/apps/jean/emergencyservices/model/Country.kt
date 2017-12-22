package com.apps.jean.emergencyservices.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "country")
data class Country (
        @PrimaryKey(autoGenerate = true) var id: Long,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "main") var main : String,
        @ColumnInfo(name = "police") var police: String,
        @ColumnInfo(name = "ambulance") var ambulance: String,
        @ColumnInfo(name = "fire") var fire: String
)
