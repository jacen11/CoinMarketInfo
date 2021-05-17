package dev.dpastukhov.coinmarketinfo.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dev.dpastukhov.coinmarketinfo.R
import dev.dpastukhov.coinmarketinfo.databinding.CoinListFragmentBinding
import dev.dpastukhov.coinmarketinfo.di.DaggerCoinComponent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import javax.inject.Inject


class CoinListFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by viewModels<CoinListViewModel> { factory }
    private lateinit var binding: CoinListFragmentBinding
    private val mainListAdapter: CoinAdapter = CoinAdapter()

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

        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, getString(R.string.error_text), Snackbar.LENGTH_LONG).show()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initSwipeToRefresh()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainListAdapter
        }
    }

    private fun initAdapter() {

        lifecycleScope.launchWhenCreated {
            mainListAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.swipeRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.listData?.collectLatest {
                mainListAdapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            mainListAdapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.recyclerView.scrollToPosition(0) }
        }
    }

    private fun initSwipeToRefresh() {
        binding.swipeRefresh.setOnRefreshListener { mainListAdapter.refresh() }
    }

}