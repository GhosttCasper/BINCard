package com.example.bincard.data

import androidx.room.Dao
import androidx.room.Query
import com.example.bincard.data.entities.Bank
import com.example.bincard.data.entities.Bin
import com.example.bincard.data.entities.Country
import com.example.bincard.data.entities.Number
import com.example.bincard.network.BinModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BinDao : BaseDao<Bin> {
    @Query("SELECT * FROM bins")
    fun getBins(): Flow<List<Bin>>

    @Query("SELECT * FROM bins WHERE id = :id")
    fun getBin(id: Long): Flow<Bin>

    // Room matches column's names with field names of result
    @Query(
        "SELECT bins.bin, numbers.length, numbers.luhn, bins.scheme, bins.type, bins.brand, bins.prepaid, " +
                "countries.numeric, countries.alpha2, countries.country_name, countries.emoji, countries.currency, " +
                "countries.latitude, countries.longitude, banks.name, banks.url, banks.phone, banks.city " +
                "FROM bins " +
                "INNER JOIN numbers ON numbers.id = bins.number_id " +
                "INNER JOIN countries ON countries.id = bins.country_id " +
                "INNER JOIN banks ON banks.id = bins.bank_id"
    )
    fun getBinModels(): Flow<List<BinModel>>
}

@Dao
interface BankDao : BaseDao<Bank> {
    @Query("SELECT * FROM banks WHERE id = :id")
    fun getBin(id: Long): Flow<Bank>
}

@Dao
interface CountryDao : BaseDao<Country> {
    @Query("SELECT * FROM countries WHERE id = :id")
    fun getBin(id: Long): Flow<Country>
}

@Dao
interface NumberDao : BaseDao<Number> {
    @Query("SELECT * FROM numbers WHERE id = :id")
    fun getBin(id: Long): Flow<Number>
}