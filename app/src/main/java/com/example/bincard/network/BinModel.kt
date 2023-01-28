package com.example.bincard.network

import androidx.room.Embedded

data class BinModel(
    var bin: String? = "",
    @Embedded
    val number: NumberModel = NumberModel(),
    val scheme: String? = "",
    val type: String? = "",
    val brand: String? = "",
    val prepaid: Boolean? = null,
    @Embedded
    val country: CountryModel = CountryModel(),
    @Embedded
    val bank: BankModel = BankModel(),
)