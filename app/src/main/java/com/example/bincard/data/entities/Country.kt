package com.example.bincard.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo
    val numeric: String? = "",
    @ColumnInfo
    val alpha2: String? = "",
    @ColumnInfo(name = "country_name")
    val name: String? = "",
    @ColumnInfo
    val emoji: String? = "",
    @ColumnInfo
    val currency: String? = "",
    @ColumnInfo
    val latitude: Int? = null,
    @ColumnInfo
    val longitude: Int? = null,
)