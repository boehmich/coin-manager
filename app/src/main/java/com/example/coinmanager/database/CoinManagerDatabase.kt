package com.example.coinmanager.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coinmanager.Coin
import com.example.coinmanager.CoinApi


@Database(entities = arrayOf(Coin::class, CoinApi::class), version = 1)
abstract class CoinManagerDatabase: RoomDatabase() {
    abstract fun getCoinDao(): CoinDAO
}