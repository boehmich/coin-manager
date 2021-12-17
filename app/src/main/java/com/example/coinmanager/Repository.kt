package com.example.coinmanager

import android.util.Log
import com.example.coinmanager.database.CoinManagerDatabase
import com.example.coinmanager.web.CoinWebService
import com.example.coinmanager.models.CoinUpdateWebEntity
import com.example.coinmanager.models.CoinWebEntity
import com.example.coinmanager.models.CoinlistCoin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

lateinit var repository: Repository

class Repository(
    private val database: CoinManagerDatabase,
    private val coinWebService: CoinWebService) {


    fun getCoinsFromWebservice(onComplete: (ArrayList<CoinlistCoin>) -> Unit) {
        coinWebService.getAllCoins(100,"EUR").enqueue(object : Callback<CoinWebEntity> {
            override fun onResponse(
                call: Call<CoinWebEntity>,
                response: Response<CoinWebEntity>
            ) {
                val coins = response.body()?.data

                val coinListCoins = coins?.map {
                    CoinlistCoin(it.id, it.name, it.symbol, it.slug, it.quote?.currency?.price,
                        it.quote?.currency?.percent_change_1h, it.quote?.currency?.percent_change_24h,
                        it.quote?.currency?.percent_change_7d)
                }

                if(coinListCoins != null){
                    onComplete.invoke(coinListCoins as ArrayList<CoinlistCoin>)
                }
            }
            override fun onFailure(call: Call<CoinWebEntity>, t: Throwable) {
                Log.e("HTTP", "Get all coins faild!", t)
            }
        })
    }

    val coins = database.getCoinDao().readAll()

    fun saveCoin(coin: CoinWithUpdate){
        var isInList = false

        val coinApiIds = database.getCoinDao().readCoinApiIds()
        coinApiIds.forEach {
            if (it == coin.coinWithUpdate.id) isInList = true
        }

        if(!isInList) database.getCoinDao().createCoinApi(coin.coinWithUpdate)

        database.getCoinDao().createCoin(coin.coin)
    }

    fun updateCoinsWatchlist() {
        val ids = database.getCoinDao().readCoinApiIds()

        var queryParams = ""
        ids.forEach {
            if(queryParams == "") queryParams += it else queryParams += ",${it}"
        }
        Log.e("ids", queryParams)

        coinWebService.getCoinsForUpdate(queryParams,"EUR").enqueue(object : Callback<CoinUpdateWebEntity> {
            override fun onResponse(
                call: Call<CoinUpdateWebEntity>,
                response: Response<CoinUpdateWebEntity>
            ) {
                val coins = response.body()

                coins?.data?.forEach { (id, body) ->
                    val price = body.quote.currency.price
                    Log.e("$id", "$price")
                    database.getCoinDao().updateCoinApiPrice(id,price)
                }

            }
            override fun onFailure(call: Call<CoinUpdateWebEntity>, t: Throwable) {
                Log.e("HTTP", "Get coins for update faild", t)
            }
        })
    }
}

/*
var coinlistList = MutableLiveData(ArrayList<CoinlistCoin>())

fun getCoinsFromWebservice() {
    coinWebService.getAllCoins(100,"EUR").enqueue(object : Callback<CoinWebEntity> {
        override fun onResponse(
            call: Call<CoinWebEntity>,
            response: Response<CoinWebEntity>
        ) {
            val coins = response.body()?.data

            val coinListCoins = coins?.map {
                CoinlistCoin(it.id, it.name, it.symbol, it.slug, it.quote?.currency?.price, it.quote?.currency?.percent_change_1h, it.quote?.currency?.percent_change_24h, it.quote?.currency?.percent_change_7d)
            }

            if(coinListCoins != null){
                repository.coinlistList = coinListCoins as MutableLiveData<ArrayList<CoinlistCoin>>
            }
        }
        override fun onFailure(call: Call<CoinWebEntity>, t: Throwable) {
            Log.e("HTTP", "Get all coins faild!", t)
        }
    })
}

 */



