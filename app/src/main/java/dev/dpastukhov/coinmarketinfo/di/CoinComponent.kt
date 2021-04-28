package dev.dpastukhov.coinmarketinfo.di

import dagger.BindsInstance
import dagger.Component
import dev.dpastukhov.coinmarketinfo.presentation.CoinListFragment

@Component(
    modules = [
        CoinModule::class,
        PresentationModule::class,
    ]
)
interface CoinComponent {

    fun inject(coinListFragment: CoinListFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun fragment(coinListFragment: CoinListFragment): Builder

        fun build(): CoinComponent
    }
}