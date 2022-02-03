package com.example.hardgame.datamodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel: ViewModel() {

    val text: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val parcedData: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }
}