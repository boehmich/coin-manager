package com.example.coinmanager

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.coinmanager.database.CoinManagerDatabase
import com.example.coinmanager.web.CoinWebService
import kotlinx.coroutines.awaitAll
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

lateinit var repository: Repository

class Repository(
    private val database: CoinManagerDatabase,
    private val coinWebService: CoinWebService
) {
    val coins = database.getCoinDao().readAll()

    fun saveCoin(coin: Coin) = database.getCoinDao().createCoin(coin)

    fun saveCoinApi(coinApi: CoinApi) = database.getCoinDao().createCoinApi(coinApi)


    fun getCoinsFromWebservice(onComplete: (ArrayList<CoinlistCoin>) -> Unit) {
        coinWebService.getAllCoins(100,"EUR").enqueue(object : Callback<CoinWebEntity> {
            override fun onResponse(
                call: Call<CoinWebEntity>,
                response: Response<CoinWebEntity>
            ) {
                val coins = response.body()?.data

                val coinListCoins = coins?.map {
                    CoinlistCoin(it.id, it.name, it.symbol, it.slug, it.quote?.EUR?.price, it.quote?.EUR?.percent_change_1h, it.quote?.EUR?.percent_change_24h, it.quote?.EUR?.percent_change_7d)
                }

                if(coinListCoins != null){
                    onComplete.invoke(coinListCoins as ArrayList<CoinlistCoin>)
                }
            }
            override fun onFailure(call: Call<CoinWebEntity>, t: Throwable) {
                Log.e("HTTP", "Get all coins faild", t)
            }
        })
    }

    fun updateCoinsDAO() {
        /** ToDo
         * get ApiIDs of coins from CoinUpdate
         * generate QueryAdress
         * update
         */

    }
}
