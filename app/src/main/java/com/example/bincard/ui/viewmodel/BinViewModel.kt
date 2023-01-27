package com.example.bincard.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bincard.data.BinDatabase
import com.example.bincard.data.entities.Bank
import com.example.bincard.data.entities.Bin
import com.example.bincard.data.entities.Country
import com.example.bincard.data.entities.Number
import com.example.bincard.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class BinApiStatus { LOADING, ERROR, DONE, NO_DATA }

private const val TAG_ERROR = "Error"
private const val TAG_RETROFIT = "Retrofit"

class BinViewModel(private val binDatabase: BinDatabase) : ViewModel() {
    // Create properties to represent MutableLiveData and LiveData for the API status
    private val _status = MutableLiveData<BinApiStatus>()
    val status: LiveData<BinApiStatus> = _status

    // Create properties to represent MutableLiveData and LiveData for a list of Bins objects
    private val _bins = MutableLiveData<List<BinModel>>()
    val bins: LiveData<List<BinModel>> = _bins

    //  Create properties to represent MutableLiveData and LiveData for a single BinModel object.
    //  This will be used to display the details of an BinModel when a list item is clicked
    private val _bin = MutableLiveData<BinModel>()
    val bin: LiveData<BinModel> = _bin

    //  Create a function that get a BinModel from the api service and sets the
    //  status via a Coroutine
    @Suppress("UNCHECKED_CAST")
    fun getBin(binInput: String) {
        viewModelScope.launch {
            _status.value = BinApiStatus.LOADING
            try {
                _bin.value = BinApi.retrofitService.getBinDetail(binInput)
                _bin.value!!.bin = binInput
                _bins.value =
                    (_bins.value?.plus(_bin.value) ?: listOf(_bin.value)) as List<BinModel>?
                addBin(_bin.value!!)
                _status.value = BinApiStatus.DONE
            } catch (e: retrofit2.HttpException) {
                if (e.code() == 404)
                    _status.value = BinApiStatus.NO_DATA
                else
                    _status.value = BinApiStatus.ERROR
                _bin.value = BinModel()
                Log.e(TAG_RETROFIT, e.toString())
            } catch (e: Exception) {
                _status.value = BinApiStatus.ERROR
                _bin.value = BinModel()
                Log.e(TAG_ERROR, e.toString())
            }
        }
    }

    suspend fun addBin(bin: BinModel) {
        withContext(Dispatchers.IO) {
            val numberId = addNumber(bin.number)
            val countryId = addCountry(bin.country)
            val bankId = addBank(bin.bank)
            val newBin = Bin(
                bin = bin.bin,
                numberId = numberId,
                scheme = bin.scheme,
                type = bin.type,
                brand = bin.brand,
                prepaid = bin.prepaid,
                countryId = countryId,
                bankId = bankId,
            )

            binDatabase.binDao().insert(newBin)
        }
    }

    suspend fun addBank(bank: BankModel): Long {
        val newBank = Bank(name = bank.name, url = bank.url, phone = bank.phone, city = bank.city)
        val generatedId: Long = binDatabase.bankDao().insert(newBank)
        return generatedId
    }

    /* Returns an instance of the [Country] entity class.
    * This will be used to add a new entry to the Bin database.
    */
    private fun getNewCountryEntry(country: CountryModel): Country {
        return Country(
            numeric = country.numeric,
            alpha2 = country.alpha2,
            name = country.name,
            emoji = country.emoji,
            currency = country.currency,
            latitude = country.latitude,
            longitude = country.longitude
        )
    }

    suspend fun addCountry(country: CountryModel): Long {
        val newCountry = getNewCountryEntry(country)
        val generatedId: Long = binDatabase.countryDao().insert(newCountry)
        return generatedId
    }

    suspend fun addNumber(number: NumberModel): Long {
        val newNumber = Number(length = number.length, luhn = number.luhn)
        val generatedId: Long = binDatabase.numberDao().insert(newNumber)
        return generatedId
    }

    /**
     * Resets the network error flag.
     */
    fun onNetworkErrorShown() {
        _status.value = BinApiStatus.DONE
    }

    fun onBinClicked(bin: BinModel) {
        // Set the BinModel object
        _bin.value = bin
        _status.value = BinApiStatus.DONE
    }
}