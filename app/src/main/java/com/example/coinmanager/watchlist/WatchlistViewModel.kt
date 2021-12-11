package com.example.coinmanager.watchlist

import androidx.lifecycle.ViewModel
import com.example.coinmanager.repository

class WatchlistViewModel : ViewModel() {

    fun createCoinsWatchlist() = repository.updateCoinsDAO()

    fun readCoinWatchlist() = repository.coins

}