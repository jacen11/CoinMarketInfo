package dev.dpastukhov.coinmarketinfo.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class CoinModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    fun provideGeckoRetrofit(gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com/v1/")
            .build()


}