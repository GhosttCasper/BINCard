package com.example.bincard.network

data class CountryModel(
    val numeric: String? = "",
    val alpha2: String? = "",
    val name: String? = "",
    val emoji: String? = "",
    val currency: String? = "",
    val latitude: Int? = null,
    val longitude: Int? = null,
)