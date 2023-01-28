package com.example.bincard.network

import androidx.room.ColumnInfo

data class CountryModel(
    val numeric: String? = "",
    val alpha2: String? = "",
    @ColumnInfo(name = "country_name")
    val name: String? = "",
    val emoji: String? = "",
    val currency: String? = "",
    val latitude: Int? = null,
    val longitude: Int? = null,
)