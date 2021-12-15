package com.example.coinmanager.web


import com.example.coinmanager.CoinWebEntity
import com.example.coinmanager.web.model.CoinUpdateWebEntity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.io.IOException
import java.util.concurrent.TimeUnit


interface CoinWebService{

    @GET("cryptocurrency/listings/latest")
    fun getAllCoins(@Query("limit") limit: Int?,
                    @Query("convert") fiat: String?): Call<CoinWebEntity>

    @GET("cryptocurrency/quotes/latest")
    fun getCoinsForUpdate(@Query("id") id: String?,
                          @Query("convert") fiat: String?): Call<CoinUpdateWebEntity>

}

fun createWebService(): CoinWebService{

    val httpClient = OkHttpClient.Builder().apply {
        addInterceptor(
            Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.header("X-CMC_PRO_API_KEY", "18c5cb41-d6d7-45b7-85d7-41e1e2093b8c")
                builder.header("Content-Type", "application/json")
                return@Interceptor chain.proceed(builder.build())
            }
        )
    }.build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://pro-api.coinmarketcap.com/v1/")
        .addConverterFactory(JacksonConverterFactory.create())
        .client(httpClient)
        .build()

    return retrofit.create(CoinWebService::class.java)
}

/*
    val httpClient = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
       .build()
 */