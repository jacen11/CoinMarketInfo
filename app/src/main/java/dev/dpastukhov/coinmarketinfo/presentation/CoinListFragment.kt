package dev.dpastukhov.coinmarketinfo.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.internal.DaggerCollections
import dev.dpastukhov.coinmarketinfo.di.DaggerCoinComponent

class CoinListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerCoinComponent.builder().fragment(this).build().inject(this)

    }
}