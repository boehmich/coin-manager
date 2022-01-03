package com.example.coinmanager.coin

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.coinmanager.CoinWithUpdate
import com.example.coinmanager.repository

class CoinEditViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val selectedCoin = savedStateHandle.get<CoinWithUpdate>("coin")!!

    fun coinPriceChanged(newPrice: Double) {
        selectedCoin.coin.pricePurchased = newPrice
    }

    fun coinExchangeChanged(newExchange: String) {
        selectedCoin.coin.exchange = newExchange
    }

    fun coinDateChanged(date: String){
        selectedCoin.coin.datePurchased = date
    }

    fun updateCoin() {
        repository.updateCoin(selectedCoin.coin)
    }
}