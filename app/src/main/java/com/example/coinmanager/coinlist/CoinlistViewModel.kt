package com.example.coinmanager.coinlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coinmanager.Coin
import com.example.coinmanager.CoinApi
import com.example.coinmanager.CoinlistCoin
import com.example.coinmanager.repository

class CoinlistViewModel : ViewModel() {

    private val coinlistLiveData = MutableLiveData<ArrayList<CoinlistCoin>>()
    val coinlist: LiveData<ArrayList<CoinlistCoin>> get() = coinlistLiveData

    fun createCoinsCoinlist() = repository.getCoinsFromWebservice{ list ->
        for (coin in list){
            Log.e("CoinId ${coin.id}", "${coin.name}, ${coin.slug}, ${coin.percent1h}, ${coin.price}")
        }
        processData(list)
        //coinlistLiveData.postValue(list)
    }

    private fun processData(coinlist: ArrayList<CoinlistCoin>) {
        // Once you get the data you may want to modify it before displaying it.

        coinlistLiveData.postValue(coinlist)
    }
}


/**
        fun createDummies() {
        val coinApiOne = CoinApi(1,"Bitcoin","BTC","bitcoin",45000.0)
        val coinApiTwo = CoinApi(2,"Etherium","ETH", "etherium", 4000.0)

        repository.saveCoinApi(coinApiOne)
        repository.saveCoinApi(coinApiTwo)

        val coinOne = Coin(1,43000.0,12.0)
        val coinTwo = Coin(1, 48000.0, -1.2)
        val coinThree = Coin(2, 5000.0, -4.7)
        val coinFour = Coin(2, 1000.0, 47.7)

        repository.saveCoin(coinOne)
        repository.saveCoin(coinTwo)
        repository.saveCoin(coinThree)
        repository.saveCoin(coinFour)
        }
 */