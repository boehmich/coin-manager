package com.example.coinmanager.coin

import androidx.lifecycle.ViewModel
import com.example.coinmanager.CoinApi
import com.example.coinmanager.repository

class CoinViewModel : ViewModel() {

    fun save(coinApi: CoinApi) {

        repository.saveCoin(coinApi)
    }
}