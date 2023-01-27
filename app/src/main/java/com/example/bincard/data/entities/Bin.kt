package com.example.bincard.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "bins",
    foreignKeys = [ForeignKey(
        entity = Number::class, parentColumns = ["id"], childColumns = ["number_id"]
    ), ForeignKey(
        entity = Country::class, parentColumns = ["id"], childColumns = ["country_id"]
    ), ForeignKey(
        entity = Bank::class, parentColumns = ["id"], childColumns = ["bank_id"]
    )]
)
data class Bin(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo
    var bin: String? = "",
    @ColumnInfo(name = "number_id", index = true)
    val numberId: Long = 0,
    @ColumnInfo
    val scheme: String? = "",
    @ColumnInfo
    val type: String? = "",
    @ColumnInfo
    val brand: String? = "",
    @ColumnInfo
    val prepaid: Boolean? = null,
    @ColumnInfo(name = "country_id", index = true)
    val countryId: Long = 0,
    @ColumnInfo(name = "bank_id", index = true)
    val bankId: Long = 0,
)