package com.example.coinmanager.coin

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.coinmanager.*
import com.example.coinmanager.models.CoinlistCoin
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
    ) : ViewModel() {

    val selectedCoin = savedStateHandle.get<CoinlistCoin>("coin")!!

    fun save(exchange: String, date: String){
        val newCoinApi = CoinApi(selectedCoin.id, selectedCoin.name, selectedCoin.symbol, selectedCoin.slug, selectedCoin.price!!)
        val newCoin = Coin(selectedCoin.id, exchange, date, selectedCoin.price!!, 0.0)
        val newCoinWithUpdate = CoinWithUpdate(newCoin, newCoinApi)

        repository.saveCoin(newCoinWithUpdate)
    }

    fun coinPriceChanged(newPrice: Double) {
        selectedCoin.price = newPrice
    }


}