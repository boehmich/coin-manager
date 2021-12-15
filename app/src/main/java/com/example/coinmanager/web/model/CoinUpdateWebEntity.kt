package com.example.coinmanager.web.model


import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
data class CoinUpdateWebEntity(
    @JsonProperty("data")
    val data: Map<Int, MapValue>
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    data class MapValue(
        @JsonProperty("quote")
        val quote: Quote
    ) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        data class Quote(
            @JsonProperty("USD")
            @JsonAlias("EUR", "BTC")
            val currency: Currency
        ) {
            @JsonIgnoreProperties(ignoreUnknown = true)
            data class Currency(
                @JsonProperty("price")
                val price: Double
            )
        }
    }
}