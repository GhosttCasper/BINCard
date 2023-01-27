package com.example.bincard.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "banks")
data class Bank(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo
    val name: String? = "",
    @ColumnInfo
    val url: String? = "",
    @ColumnInfo
    val phone: String? = "",
    @ColumnInfo
    val city: String? = "",
)