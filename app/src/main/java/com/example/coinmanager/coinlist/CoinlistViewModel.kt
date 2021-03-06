package com.example.coinmanager.coinlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coinmanager.*
import com.example.coinmanager.models.CoinlistCoin
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinlistViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val coinlistLiveData = MutableLiveData<ArrayList<CoinlistCoin>>()
    val coinlist: LiveData<ArrayList<CoinlistCoin>> get() = coinlistLiveData

    fun updateCoinsCoinlist() = repository.getCoinsFromWebservice{ list ->
        /*
        for (coin in list){
            Log.e("CoinId ${coin.id}", "${coin.name}, ${coin.slug}, ${coin.percent1h}, ${coin.price}")
        } */
        //repository.coinlistLiveData.postValue(list)
        processData(list)
    }


    private fun processData(coinlist: ArrayList<CoinlistCoin>) {

        coinlist.forEach{
            it.percent1h = Math.round(it.percent1h!! * 100.0) / 100.0
            it.percent24h = Math.round(it.percent24h!! * 100.0) / 100.0
            it.percent7d = Math.round(it.percent7d!! * 100.0) / 100.0
            it.price = Math.round(it.price!! * 100000.0) / 100000.0
        }

        coinlistLiveData.postValue(coinlist)
    }

}

/*
val coinlist: LiveData<ArrayList<CoinlistCoin>> = Transformations.map(repository.coinlistLiveData){ list ->
        list.forEach {
            it.percent1h = Math.round(it.percent1h!! * 100.0) / 100.0
            it.percent24h = Math.round(it.percent24h!! * 100.0) / 100.0
            it.percent7d = Math.round(it.percent7d!! * 100.0) / 100.0
            it.price = Math.round(it.price!! * 100000.0) / 100000.0
        }
        return@map list
    }
 */
