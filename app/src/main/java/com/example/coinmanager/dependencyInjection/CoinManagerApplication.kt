package com.example.coinmanager.dependencyInjection

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@HiltAndroidApp
class CoinManagerApplication: Application() {
}

