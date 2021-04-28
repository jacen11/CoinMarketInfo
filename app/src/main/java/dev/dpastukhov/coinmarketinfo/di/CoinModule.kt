package dev.dpastukhov.coinmarketinfo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dev.dpastukhov.coinmarketinfo.ViewModelFactory
import dev.dpastukhov.coinmarketinfo.ViewModelKey
import dev.dpastukhov.coinmarketinfo.data.CoinMarketServiceApi
import dev.dpastukhov.coinmarketinfo.presentation.CoinListViewModel
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Provider
import javax.inject.Singleton

@Module
class CoinModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    fun provideGeckoRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://pro-api.coinmarketcap.com/v1/")
        .build()

    @Provides
    fun provideService(retrofit: Retrofit): CoinMarketServiceApi = retrofit.create()



}

@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinListViewModel::class)
    abstract fun provideViewModel(model: CoinListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory?): ViewModelProvider.Factory?
}
