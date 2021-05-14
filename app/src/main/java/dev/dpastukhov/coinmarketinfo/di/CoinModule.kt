package dev.dpastukhov.coinmarketinfo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dev.dpastukhov.coinmarketinfo.BuildConfig
import dev.dpastukhov.coinmarketinfo.ViewModelFactory
import dev.dpastukhov.coinmarketinfo.ViewModelKey
import dev.dpastukhov.coinmarketinfo.data.CoinMarketServiceApi
import dev.dpastukhov.coinmarketinfo.presentation.CoinListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
class CoinModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    fun provideGeckoRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl("https://pro-api.coinmarketcap.com/v1/")
        .client(getClient())
        .build()

    @Provides
    fun provideService(retrofit: Retrofit): CoinMarketServiceApi = retrofit.create()

    private fun getClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) BODY else BASIC
        val okHttp: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttp.addInterceptor(logging)
        okHttp.addInterceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.header("Accept", "application/json")
            requestBuilder.header("X-CMC_PRO_API_KEY", "fbbe5594-21dc-4a0f-9980-0d12549f7733")
            chain.proceed(requestBuilder.build())
        }
        return okHttp.build()
    }
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
