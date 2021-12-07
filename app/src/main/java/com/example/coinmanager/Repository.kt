package com.example.coinmanager

import com.example.coinmanager.database.CoinManagerDatabase

lateinit var repository: Repository

class Repository(
    private val database: CoinManagerDatabase,
    //private val contactWebService: ContactWebService
) {
    fun getCoinsDAO() = database.getCoinDao().readAll()

    fun saveCoin(coinApi: CoinApi) = database.getCoinDao().create(coinApi)

    fun updateCoinsDAO() {
        /** ToDo
         * get ApiIDs of coins from CoinUpdate
         * generate QueryAdress
         * update
         */

    }
}