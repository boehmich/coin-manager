package com.example.coinmanager

import androidx.room.*
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
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



data class CoinlistCoin(
    var id: Int,
    var name: String,
    var symbol: String,
    var slug: String,
    var price: Double?,
    var percent1h: Double?,
    var percent24h: Double?,
    var percent7d: Double?
)


@JsonIgnoreProperties(ignoreUnknown = true)
data class CoinWebEntity(
    @JsonProperty("data")
    var data: List<Data>?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Data(
    @JsonProperty("id") var id: Int,
    @JsonProperty("name") var name: String,
    @JsonProperty("symbol") var symbol: String,
    @JsonProperty("slug") var slug: String,

    @JsonProperty("quote")
    val quote: Quote?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Quote(
    @JsonProperty("EUR")
    var EUR: EUR?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class EUR(
    @JsonProperty("price") var price: Double,
    @JsonProperty("percent_change_1h") var percent_change_1h: Double,
    @JsonProperty("percent_change_24h") var percent_change_24h: Double,
    @JsonProperty("percent_change_7d") var percent_change_7d: Double
)



