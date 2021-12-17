package com.example.coinmanager.models

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CoinWebEntity(
    @JsonProperty("data")
    var data: List<Data>?
){
    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Data(
        @JsonProperty("id") var id: Int,
        @JsonProperty("name") var name: String,
        @JsonProperty("symbol") var symbol: String,
        @JsonProperty("slug") var slug: String,

        @JsonProperty("quote")
        val quote: Quote?
    ){
        @JsonIgnoreProperties(ignoreUnknown = true)
        data class Quote(
            @JsonProperty("USD")
            @JsonAlias("EUR", "BTC")
            var currency: Currency?
        ){
            @JsonIgnoreProperties(ignoreUnknown = true)
            data class Currency(
                @JsonProperty("price") var price: Double,
                @JsonProperty("percent_change_1h") var percent_change_1h: Double,
                @JsonProperty("percent_change_24h") var percent_change_24h: Double,
                @JsonProperty("percent_change_7d") var percent_change_7d: Double
            )
        }
    }
}