package com.example.coinmanager.watchlist

import androidx.lifecycle.LiveData

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.coinmanager.CoinWithUpdate
import com.example.coinmanager.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val readCoinsWatchlist: LiveData<List<CoinWithUpdate>> = Transformations.map(repository.coins) { list ->
        list.forEach {
            val percent = (it.coinWithUpdate.priceActual / it.coin.pricePurchased - 1) * 100
            it.coin.priceChangedPercent = Math.round(percent * 100.0) / 100.0
            it.coin.pricePurchased = Math.round(it.coin.pricePurchased * 100.0) / 100.0
            it.coinWithUpdate.priceActual = Math.round(it.coinWithUpdate.priceActual * 100.0) / 100.0
        }
        return@map list
    }

    fun updateCoinsWatchlist() = repository.updateCoinsWatchlist()

}