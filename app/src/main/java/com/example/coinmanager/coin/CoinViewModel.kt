package com.example.coinmanager.coin

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.coinmanager.*
import com.example.coinmanager.models.CoinlistCoin

class CoinViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val selectedCoin = savedStateHandle.get<CoinlistCoin>("coin")!!

    fun save(){
        val newCoinApi = CoinApi(selectedCoin.id, selectedCoin.name, selectedCoin.symbol, selectedCoin.slug, selectedCoin.price!!)
        val newCoin = Coin(selectedCoin.id, selectedCoin.price!!, 0.0)
        val newCoinWithUpdate = CoinWithUpdate(newCoin, newCoinApi)

        repository.saveCoin(newCoinWithUpdate)
    }


}