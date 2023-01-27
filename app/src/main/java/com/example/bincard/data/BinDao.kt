package com.example.bincard.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bincard.data.entities.Bank
import com.example.bincard.data.entities.Bin
import com.example.bincard.data.entities.Country
import com.example.bincard.data.entities.Number
import kotlinx.coroutines.flow.Flow

@Dao
interface BinDao : BaseDao<Bin> {
    @Query("SELECT * FROM bin_table")
    fun getBins(): Flow<List<Bin>>

    @Query("SELECT * FROM bin_table WHERE id = :id")
    fun getBin(id: Long): Flow<Bin>

    /*@Query(
        "SELECT * FROM bin_table " +
                "INNER JOIN bank ON bank.id = bin_table.bank_id " +
                "INNER JOIN country ON country.id = loan.user_id " +
                "INNER JOIN country ON country.id = loan.user_id " +
                "WHERE user.name LIKE :userName"
    )
    fun findBooksBorrowedByNameSync(userName: String): Flow<List<BinModel>>*/
}

@Dao
interface BankDao : BaseDao<Bank> {
    @Query("SELECT * FROM bank WHERE id = :id")
    fun getBin(id: Long): Flow<Bank>
}

@Dao //: BaseDao<Country>
interface CountryDao {
    @Insert
    fun insert(country: Country): Long

    @Query("SELECT * FROM country WHERE id = :id")
    fun getBin(id: Long): Flow<Country>
}

@Dao
interface NumberDao : BaseDao<Number> {
    @Query("SELECT * FROM number WHERE id = :id")
    fun getBin(id: Long): Flow<Number>
}