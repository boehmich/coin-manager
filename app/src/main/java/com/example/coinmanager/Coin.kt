package com.example.coinmanager


class Coin(
    var id: Int = 0,
    var apiId: Int,
    var name: String,
    var symbol: String,
    var slug: String,
    var pricePurchased: Double
)