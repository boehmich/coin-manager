package com.example.coinmanager.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coinmanager.Coin


@Database(entities = arrayOf(Coin::class), version = 1)
abstract class CoinManagerDatabase: RoomDatabase() {
    abstract fun getCoinDao(): CoinDAO
}