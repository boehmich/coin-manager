package com.example.coinmanager.watchlist

import androidx.lifecycle.ViewModel
import com.example.coinmanager.repository

class WatchlistViewModel : ViewModel() {

    fun updateCoinsWatchlist() = repository.updateCoinsWatchlist()


    fun readCoinWatchlist() = repository.coins



}