package com.example.coinmanager.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.coinmanager.Coin

@Dao
interface CoinDAO {
    @Query("SELECT * FROM Coins ORDER BY id")
    fun readAll(): LiveData<List<Coin>>

    @Insert
    fun create(coin: Coin)

    @Query("SELECT * FROM Coins WHERE id = :id")
    fun read(id: Int): Coin

    @Update
    fun update(coin: Coin)

    @Delete
    fun delete(coin: Coin)
}