package com.example.bincard.network

data class BinModel(
    var bin: String? = "",
    val number: NumberModel = NumberModel(),
    val scheme: String? = "",
    val type: String? = "",
    val brand: String? = "",
    val prepaid: Boolean? = null,
    val country: CountryModel = CountryModel(),
    val bank: BankModel = BankModel(),
)