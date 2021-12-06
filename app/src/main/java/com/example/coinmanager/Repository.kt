package com.example.coinmanager

import com.example.coinmanager.database.CoinManagerDatabase

lateinit var repository: Repository

class Repository(
    private val database: CoinManagerDatabase,
    //private val contactWebService: ContactWebService
) {

}