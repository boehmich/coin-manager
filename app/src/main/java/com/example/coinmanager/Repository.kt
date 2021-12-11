package com.example.coinmanager

import android.util.Log
import com.example.coinmanager.database.CoinManagerDatabase
import com.example.coinmanager.web.CoinWebService
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

    fun getCoinsFromWebservice() {

        coinWebService.getAllCoins(1000,"EUR").enqueue(object : Callback<CoinWebEntity> {
            override fun onResponse(
                call: Call<CoinWebEntity>,
                response: Response<CoinWebEntity>
            ) {
                val coins = response.body()?.data
                if (coins != null) {
                    for (coin in coins){
                        Log.e("Coin ${coins.indexOf(coin)}", "${coin.id} ${coin.name} ${coin.slug} ${coin.quote?.EUR?.price}")
                    }
                }

                if (coins == null){
                    Log.e("leider","leer")
                }
                /*
                val databaseContacts = coins?.map {
                    CoinApi(it.id, it.name, it.symbol, it.slug, it.p)
                }
                if(databaseContacts != null){
                    database.getContactDao().create(databaseContacts)
                }
                 */
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
