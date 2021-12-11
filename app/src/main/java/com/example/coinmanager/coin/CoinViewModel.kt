package com.example.coinmanager.coin

import androidx.lifecycle.ViewModel
import com.example.coinmanager.Coin
import com.example.coinmanager.repository

class CoinViewModel : ViewModel() {

    fun save(coin: Coin) {

        repository.saveCoin(coin)
    }
}