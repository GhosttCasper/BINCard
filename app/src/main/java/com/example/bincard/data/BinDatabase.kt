package com.example.bincard.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bincard.data.entities.Bank
import com.example.bincard.data.entities.Bin
import com.example.bincard.data.entities.Country
import com.example.bincard.data.entities.Number

@Database(
    entities = [Bank::class, Bin::class, Country::class, Number::class],
    version = 1,
    exportSchema = false
)
abstract class BinDatabase : RoomDatabase() {
    abstract fun binDao(): BinDao
    abstract fun bankDao(): BankDao
    abstract fun countryDao(): CountryDao
    abstract fun numberDao(): NumberDao

    companion object {
        @Volatile
        private var INSTANCE: BinDatabase? = null

        fun getDatabase(context: Context): BinDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BinDatabase::class.java,
                    "bin_database"
                ).allowMainThreadQueries() // TODO использовать корутины, получать базу данных в фоне
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}