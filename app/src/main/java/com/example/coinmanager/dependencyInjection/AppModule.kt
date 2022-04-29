package com.example.coinmanager.dependencyInjection

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coinmanager.Repository
import com.example.coinmanager.database.CoinDAO
import com.example.coinmanager.database.CoinManagerDatabase
import com.example.coinmanager.web.CoinWebService
import com.example.coinmanager.web.createWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    lateinit var database: CoinManagerDatabase
    lateinit var repository: Repository

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): CoinManagerDatabase{
        database = Room.databaseBuilder(
            appContext,
            CoinManagerDatabase::class.java,
            "CoinManager"
            )
            .allowMainThreadQueries()
            .build()
        return database
    }

    @Singleton
    @Provides
    fun provideCoinWebservice(): CoinWebService{
        return createWebService()
    }

    @Provides
    fun provideCoinDAO(coinManagerDatabase: CoinManagerDatabase): CoinDAO {
        return coinManagerDatabase.getCoinDao()
    }

    @Singleton
    @Provides
    fun provideRepository(@ApplicationContext appContext: Context): Repository {
        return Repository(provideDatabase(appContext), provideCoinWebservice())
    }

}
