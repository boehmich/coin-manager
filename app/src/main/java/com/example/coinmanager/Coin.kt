package com.example.coinmanager

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Coins")
class Coin(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var apiId: Int,
    var name: String,
    var symbol: String,
    var slug: String,
    var pricePurchased: Double
): Serializable