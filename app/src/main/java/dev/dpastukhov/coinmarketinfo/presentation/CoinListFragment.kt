package dev.dpastukhov.coinmarketinfo.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import dagger.internal.DaggerCollections
import dev.dpastukhov.coinmarketinfo.ViewModelFactory
import dev.dpastukhov.coinmarketinfo.databinding.CoinListFragmentBinding
import dev.dpastukhov.coinmarketinfo.di.DaggerCoinComponent
import javax.inject.Inject


class CoinListFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by viewModels<CoinListViewModel> { factory }
    private lateinit var binding: CoinListFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerCoinComponent.builder()
            .fragment(this)
            .build()
            .inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CoinListFragmentBinding.inflate(inflater, container, false)

        viewModel.setCoinList()
        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, "error", Snackbar.LENGTH_LONG).show()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listCoin.observe(viewLifecycleOwner) {
            binding.test.text = it.first().name
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it != null) binding.swipeRefreshLayout.isRefreshing = it
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.setCoinList()
        }
    }
}