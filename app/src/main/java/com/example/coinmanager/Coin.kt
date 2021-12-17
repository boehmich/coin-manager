package com.example.coinmanager


import androidx.room.*
import java.io.Serializable


@Entity(tableName = "Coins",
    foreignKeys = [ForeignKey(
        entity = CoinApi::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("apiId"),
        onDelete = ForeignKey.CASCADE
    )])
class Coin(
    var apiId: Int,
    var pricePurchased: Double = 0.0,
    var priceChangedPercent: Double = 0.0,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
): Serializable

@Entity(tableName = "CoinsApi")
class CoinApi(
    @PrimaryKey var id: Int,
    var name: String,
    var symbol: String,
    var slug: String,
    var priceActual: Double
): Serializable

class CoinWithUpdate(
    @Embedded
    val coin: Coin,
    @Relation(
        parentColumn = "apiId",
        entityColumn = "id"
    )
    val coinWithUpdate: CoinApi
): Serializable






