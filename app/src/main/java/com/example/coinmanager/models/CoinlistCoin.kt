package com.example.coinmanager.models

import java.io.Serializable

data class CoinlistCoin(
    var id: Int,
    var name: String,
    var symbol: String,
    var slug: String,
    var price: Double?,
    var percent1h: Double?,
    var percent24h: Double?,
    var percent7d: Double?
): Serializable