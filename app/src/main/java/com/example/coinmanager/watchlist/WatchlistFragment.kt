package com.example.coinmanager.watchlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.coinmanager.CoinWithUpdate
import com.example.coinmanager.R
import com.example.coinmanager.adapter.WatchlistAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.content.Context
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.navigation.fragment.findNavController
import com.example.coinmanager.Repository

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WatchlistFragment : Fragment(R.layout.watchlist_fragment) {

    @Inject lateinit var repository: Repository

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val viewModel: WatchlistViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewWatchlist)
        viewModel.updateCoinsWatchlist();

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshWatchlist)
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.updateCoinsWatchlist()
        }
        viewModel.readCoinsWatchlist.observe(viewLifecycleOwner, {
            refreshCoinsInWatchlist(ArrayList(it))
            swipeRefreshLayout.isRefreshing = false
        })
    }


    private fun refreshCoinsInWatchlist(coinsWatchlist: ArrayList<CoinWithUpdate>) {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = WatchlistAdapter(coinsWatchlist, onClickListener = this::handleClickListener)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

    }

    private fun handleClickListener(view: View, coin: CoinWithUpdate) {
        val materialAlertContext: Context = ContextThemeWrapper(requireContext(), R.style.Theme_CoinManager)
        val options = arrayOf(getString(R.string.edit), getString(R.string.delete))
        MaterialAlertDialogBuilder(materialAlertContext)
            .setTitle(getString(R.string.watchlist_action, coin.coinWithUpdate.name))
            .setItems(options) { _, selectedItem ->
                if(selectedItem == 0){
                    val navHostFragment = findNavController()
                    navHostFragment.navigate(WatchlistFragmentDirections.actionWatchlistFragmentToCoinEditFragment(coin))
                }
                if(selectedItem == 1){
                    deleteCoin(coin, materialAlertContext)
                }
            }
            .setCancelable(true)
            .show()
    }

    private fun deleteCoin(coin: CoinWithUpdate, context: Context) {
        MaterialAlertDialogBuilder(context)
            .setTitle(coin.coinWithUpdate.name)
            .setMessage(getString(R.string.watchlist_delete_action))
            .setPositiveButton(getString(R.string.delete)) { _, _ ->
                repository.deleteCoin(coin.coin)
            }
            .setNegativeButton(getString(R.string.cancel)) { _, _ ->
            }
            .setCancelable(false)
            .show()
    }
}