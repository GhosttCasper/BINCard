package com.example.bincard

import android.app.Application
import com.example.bincard.data.BinDatabase

/**
 * An application class that inherits from [Application], allows for the creation of a singleton
 * instance of the [BinDatabase]
 */

class BaseApplication : Application() {
    // Using by lazy so the database is only created when needed
    // rather than when the application starts
    val database: BinDatabase by lazy { BinDatabase.getDatabase(this) }
}