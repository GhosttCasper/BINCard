package com.example.bincard.network

data class Bin(
    var bin: String? = "",
    val number: Number? = Number(),
    val scheme: String? = "",
    val type: String? = "",
    val brand: String? = "",
    val prepaid: Boolean? = null,
    val country: Country? = Country(),
    val bank: Bank? = Bank(),
)