package com.example.bincard.network

data class Bin(
    var bin: String?,
    val number: Number?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: Country?,
    val bank: Bank?,
)