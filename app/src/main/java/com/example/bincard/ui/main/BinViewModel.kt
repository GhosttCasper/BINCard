package com.example.bincard.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bincard.network.Bin
import com.example.bincard.network.BinApi
import kotlinx.coroutines.launch

enum class BinApiStatus { LOADING, ERROR, DONE }

class BinViewModel : ViewModel() {
    // Create properties to represent MutableLiveData and LiveData for the API status
    private val _status = MutableLiveData<BinApiStatus>()
    val status: LiveData<BinApiStatus> = _status

    // Create properties to represent MutableLiveData and LiveData for a list of Bins objects
    private val _bins = MutableLiveData<List<Bin>>()
    val bins: LiveData<List<Bin>> = _bins

    //  Create properties to represent MutableLiveData and LiveData for a single Bin object.
    //  This will be used to display the details of an Bin when a list item is clicked
    private val _bin = MutableLiveData<Bin>()
    val bin: LiveData<Bin> = _bin

    //  Create a function that get a Bin from the api service and sets the
    //  status via a Coroutine
    fun getBin(binInput: String) {
        viewModelScope.launch {
            _status.value = BinApiStatus.LOADING
            try {
                _bin.value = BinApi.retrofitService.getBinDetail(binInput)
                _bins.value?.plus(_bin.value)
                _status.value = BinApiStatus.DONE
            } catch (e: Exception) {
                _status.value = BinApiStatus.ERROR
                //_bin.value = null
            }
        }
    }

    fun onBinClicked(bin: Bin) {
        // Set the Bin object
        _bin.value = bin
    }
}