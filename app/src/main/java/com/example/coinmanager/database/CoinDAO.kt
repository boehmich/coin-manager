package com.example.coinmanager.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.coinmanager.Coin
import com.example.coinmanager.CoinApi
import com.example.coinmanager.CoinWithUpdate

@Dao
interface CoinDAO {

    @Transaction
    @Query("SELECT * FROM Coins ORDER BY id")
    fun readAll(): LiveData<List<CoinWithUpdate>>

    @Insert
    fun createCoin(coin: Coin)

    @Insert
    fun createCoinApi(coinApi: CoinApi)

    @Transaction
    @Query("SELECT * FROM Coins WHERE id = :id")
    fun read(id: Int): CoinWithUpdate

    @Update
    fun updateCoin(coin: Coin)

    @Update
    fun updateCoinApi(coinApi: CoinApi)

    @Delete
    fun delete(coin: Coin)

    @Query("DELETE FROM Coins")
    fun deleteTable()
}